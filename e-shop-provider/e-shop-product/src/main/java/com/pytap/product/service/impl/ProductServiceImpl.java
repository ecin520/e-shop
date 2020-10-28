package com.pytap.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.api.model.dto.FlashSaleDTO;
import com.pytap.api.service.api.sale.CarouselFeignService;
import com.pytap.api.service.api.sale.FlashSaleFeignService;
import com.pytap.api.service.api.sale.NewProductRecommendFeignService;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.common.utils.ResultEntity;
import com.pytap.common.utils.UUIDUtil;
import com.pytap.generator.dao.EsProductMapper;
import com.pytap.generator.dao.EsProductSpecDetailMapper;
import com.pytap.generator.dao.EsSkuProductMapper;
import com.pytap.generator.dao.EsSkuSpecDetailMapper;
import com.pytap.generator.entity.*;
import com.pytap.product.model.dto.ProductDTO;
import com.pytap.product.model.dto.SkuProductDTO;
import com.pytap.product.model.vo.*;
import com.pytap.product.service.ProductService;
import com.pytap.product.service.SkuProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/9 16:23
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Resource
    private SkuProductService skuProductService;

    @Resource
    private EsProductMapper productMapper;

    @Resource
    private EsSkuProductMapper skuProductMapper;

    @Resource
    private EsSkuSpecDetailMapper skuSpecDetailMapper;

    @Resource
    private EsProductSpecDetailMapper productSpecDetailMapper;

    @Resource
    private FlashSaleFeignService flashSaleFeignService;

    @Resource
    private NewProductRecommendFeignService newProductRecommendFeignService;

    @Resource
    private CarouselFeignService carouselFeignService;

    @Override
    public Integer insertProduct(EsProduct product) {
        product.setCreateTime(new Date());
        // 审核设定未审核
        product.setVerifyStatus(0);
        product.setItemNo(UUIDUtil.uuidNumberString());
        return productMapper.insert(product);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer deleteProductById(Long id) {
        // 通过商品id获取sku
        EsSkuProductExample example = new EsSkuProductExample();
        EsSkuProductExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(id);

        // 删除sku_spec_detail关系,获取该商品所有sku，遍历后通过skuId删除sku_spec关联
        List<EsSkuProduct> skuProducts = skuProductMapper.selectByExample(example);
        deleteSkuSpecDetails(skuProducts);

        // 删除商品所有sku
        skuProductMapper.deleteByExample(example);

        productMapper.deleteByPrimaryKey(id);

        return 1;
    }

    @Override
    public Integer updateProduct(EsProduct product) {
        product.setUpdateTime(new Date());
        return productMapper.updateByPrimaryKeyWithBLOBs(product);
    }

    @Override
    public ProductVO getProductVO(EsProduct queryParam) {

        List<EsProduct> list = getProductListByQueryParam(queryParam);

        if (!list.isEmpty()) {
            EsProduct product = list.get(0);
            ProductVO vo = new ProductVO();

            BeanUtils.copyProperties(product, vo);

            vo.setStock(getStockByProductId(product.getId()));

            return vo;
        }

        return null;
    }

    @Override
    public ProductWebVO getProductWebVO(EsProduct queryParam) {
        
        List<EsProduct> list = getProductListByQueryParam(queryParam);

        if (!list.isEmpty()) {
            EsProduct product = list.get(0);
            ProductWebVO vo = new ProductWebVO();

            BeanUtils.copyProperties(product, vo);

            // 通过商品id查询到sku列表
            EsSkuProduct skuProduct = new EsSkuProduct();
            skuProduct.setProductId(product.getId());
            
            EsSkuProductExample example = new EsSkuProductExample();
            EsSkuProductExample.Criteria criteria = example.createCriteria();
            criteria.andProductIdEqualTo(product.getId());
            
            List<EsSkuProduct> skuProducts = skuProductMapper.selectByExample(example);

            vo.setSkuProducts(skuProducts);

            return vo;
        }
        return null;
    }

    @Override
    public EsProduct getProduct(EsProduct queryParam) {
        List<EsProduct> list = getProductListByQueryParam(queryParam);
        return !list.isEmpty() ? list.get(0) : null;
    }

    @Override
    public Pager<EsProduct> listProducts(Integer pageNum, Integer pageSize, EsProduct queryParam) {
        if (null != pageNum && null != pageSize) {
            PageHelper.startPage(pageNum, pageSize);
        } else {
            PageHelper.startPage(0, 0);
        }

        EsProductExample example = new EsProductExample();
        EsProductExample.Criteria criteria = example.createCriteria();
        // 按照创建时间降序
        example.setOrderByClause("create_time DESC");

        if (null != queryParam) {
            // spu商品名称模糊搜索
            if (null != queryParam.getName()) {
                criteria.andNameLike("%" + queryParam.getName() + "%");
            }
            // 店铺id搜索产品
            if (null != queryParam.getShopId()) {
                criteria.andShopIdEqualTo(queryParam.getShopId());
            }
            // 通过商品分类获取商品列表
            if (null != queryParam.getProductCategoryDetailId()) {
                criteria.andProductCategoryDetailIdEqualTo(queryParam.getProductCategoryDetailId());
            }
            // 上架状态
            if (null != queryParam.getShelfStatus()) {
                criteria.andShelfStatusEqualTo(queryParam.getShelfStatus());
            }
            // 审核状态
            if (null != queryParam.getVerifyStatus()) {
                criteria.andVerifyStatusEqualTo(queryParam.getVerifyStatus());
            }
            // 删除状态
            if (null != queryParam.getDeleteStatus()) {
                criteria.andDeleteStatusEqualTo(queryParam.getDeleteStatus());
            }
            // 添加店铺名模糊查找的商品
            if (null != queryParam.getShopName()) {
                criteria.andShopNameLike("%" + queryParam.getShopName() + "%");
            }
            // 添加分类详情名模糊查找的商品
            if (null != queryParam.getProductCategoryDetailName()) {
                criteria.andProductCategoryDetailNameLike("%" + queryParam.getProductCategoryDetailName() + "%");
            }
        }

        List<EsProduct> list = productMapper.selectByExampleWithBLOBs(example);

        Pager<EsProduct> pager = new Pager<>();
        pager.setPageNum(pageNum);
        pager.setPageSize(pageSize);
        pager.setContent(list);
        pager.setTotal(productMapper.countByExample(example));
        return pager;
    }

    @Transactional
    @Override
    public Integer insertProductByParam(ProductDTO productDTO) {

        // 插入商品
        EsProduct product = new EsProduct();
        BeanUtils.copyProperties(productDTO.getProduct(), product);

        product.setCreateTime(new Date());
        product.setVerifyStatus(0);
        product.setItemNo(UUIDUtil.uuidNumberString());
        productMapper.insert(product);

        // 传输对象中获取Sku列表
        List<SkuProductDTO> skuProductDTOList = productDTO.getSkuProductList();
        for (SkuProductDTO skuProductDTO : skuProductDTOList) {

            EsSkuProduct skuProduct = new EsSkuProduct();
            BeanUtils.copyProperties(skuProductDTO, skuProduct);

            skuProduct.setName(integrationName(skuProductDTO));
            skuProduct.setProductId(product.getId());
            skuProduct.setSale(0);
            skuProduct.setCreateTime(new Date());
            skuProductMapper.insert(skuProduct);

            insertSkuSpec(skuProductDTO, skuProduct);
        }

        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updateProductByParam(ProductDTO productDTO) {

        // 更新商品
        EsProduct product = new EsProduct();
        BeanUtils.copyProperties(productDTO.getProduct(), product);
        product.setUpdateTime(new Date());
        productMapper.updateByPrimaryKeyWithBLOBs(product);

        // 删除原本的sku商品
        EsSkuProductExample example = new EsSkuProductExample();
        EsSkuProductExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(product.getId());

        // 删除sku_spec_detail关系
        List<EsSkuProduct> skuProducts = skuProductMapper.selectByExample(example);
        deleteSkuSpecDetails(skuProducts);

        // 删除商品对应的所有sku
        skuProductMapper.deleteByExample(example);

        // 传输对象中获取Sku列表
        List<SkuProductDTO> skuProductDTOList = productDTO.getSkuProductList();
        for (SkuProductDTO skuProductDTO : skuProductDTOList) {
            // 插入新的sku商品
            EsSkuProduct skuProduct = new EsSkuProduct();
            BeanUtils.copyProperties(skuProductDTO, skuProduct);

            skuProduct.setName(integrationName(skuProductDTO));
            skuProduct.setProductId(product.getId());
            skuProduct.setUpdateTime(new Date());
            skuProductMapper.insert(skuProduct);

            insertSkuSpec(skuProductDTO, skuProduct);
        }

        return 1;
    }

    @Override
    public Pager<FlashSaleProductVO> listValidFlashSaleProducts(QueryParam<FlashSaleDTO> queryParam) {
        ResultEntity<Pager<EsFlashSaleProduct>> resultEntity = flashSaleFeignService.listValidFlashSaleProductsByQueryParam(queryParam);
        return getFlashSalePagerFromEntity(resultEntity);
    }

    @Override
    public Pager<FlashSaleProductVO> listFlashSaleProducts(QueryParam<FlashSaleDTO> queryParam) {
        ResultEntity<Pager<EsFlashSaleProduct>> resultEntity = flashSaleFeignService.listFlashSaleProductsByQueryParam(queryParam);
        return getFlashSalePagerFromEntity(resultEntity);
    }

    @Override
    public Pager<NewProductRecommendVO> listNewProductRecommends(Integer pageNum, Integer pageSize) {
        ResultEntity<Pager<EsNewProductRecommend>> resultEntity = newProductRecommendFeignService.listNewProductsRecommend(pageNum, pageSize);

        Pager<NewProductRecommendVO> pager = new Pager<>();
        List<NewProductRecommendVO> newProductRecommends = new ArrayList<>(16);

        if (200 == resultEntity.getCode()) {
            if (null != resultEntity.getData()) {
                if (null != resultEntity.getData().getContent()) {
                    List<EsNewProductRecommend> list = resultEntity.getData().getContent();
                    for (EsNewProductRecommend newProductRecommend : list) {

                        // 将新品推荐参数复制到vo
                        NewProductRecommendVO vo = new NewProductRecommendVO();
                        BeanUtils.copyProperties(newProductRecommend, vo);

                        // 通过商品id查询商品
                        EsProduct product = new EsProduct();
                        product.setId(newProductRecommend.getProductId());
                        product = getProduct(product);

                        vo.setProduct(product);

                        newProductRecommends.add(vo);
                    }
                    pager.setTotal(resultEntity.getData().getTotal());
                    pager.setPageNum(resultEntity.getData().getPageNum());
                    pager.setPageSize(resultEntity.getData().getPageSize());
                    pager.setContent(newProductRecommends);
                    return pager;
                }
            }
        } else {
            logger.error(resultEntity.getMessage());
        }
        return null;
    }

    @Override
    public Pager<CarouselProductVO> listCarouselProducts(QueryParam<EsProductCarousel> queryParam) {
        ResultEntity<Pager<EsProductCarousel>> resultEntity = carouselFeignService.listProductCarousels(queryParam);
        Pager<CarouselProductVO> pager = new Pager<>();
        List<CarouselProductVO> carouselProducts = new ArrayList<>(16);

        if (200 == resultEntity.getCode()) {
            if (null != resultEntity.getData()) {
                if (null != resultEntity.getData().getContent()) {
                    List<EsProductCarousel> productCarousels = resultEntity.getData().getContent();

                    for (EsProductCarousel productCarousel : productCarousels) {

                        CarouselProductVO vo = new CarouselProductVO();
                        BeanUtils.copyProperties(productCarousel, vo);

                        EsProduct product = new EsProduct();
                        product.setId(productCarousel.getProductId());
                        product = getProduct(product);

                        vo.setProduct(product);

                        carouselProducts.add(vo);
                    }

                    pager.setTotal(resultEntity.getData().getTotal());
                    pager.setPageNum(resultEntity.getData().getPageNum());
                    pager.setPageSize(resultEntity.getData().getPageSize());
                    pager.setContent(carouselProducts);
                    return pager;
                }
            }
        } else {
            logger.error(resultEntity.getMessage());
        }
        return null;
    }

    private Pager<FlashSaleProductVO> getFlashSalePagerFromEntity(ResultEntity<Pager<EsFlashSaleProduct>> resultEntity) {
        Pager<FlashSaleProductVO> pager = new Pager<>();
        List<FlashSaleProductVO> products = new ArrayList<>(16);

        if (200 == resultEntity.getCode()) {
            if (null != resultEntity.getData()) {
                if (null != resultEntity.getData().getContent()) {
                    List<EsFlashSaleProduct> list = resultEntity.getData().getContent();
                    for (EsFlashSaleProduct flashSaleProduct : list) {

                        // 将秒杀商品类复制到vo中
                        FlashSaleProductVO vo = new FlashSaleProductVO();
                        BeanUtils.copyProperties(flashSaleProduct, vo);

                        // 通过商品id查询商品
                        EsProduct product = new EsProduct();
                        product.setId(flashSaleProduct.getProductId());
                        product = getProduct(product);

                        EsSkuProduct skuProduct = new EsSkuProduct();
                        skuProduct.setId(flashSaleProduct.getSkuProductId());
                        skuProduct = skuProductService.getSkuProduct(skuProduct);
                        vo.setSkuProduct(skuProduct);

                        // 获取商品库存，也就是计算sku库存总和，添加到ProductVO
                        ProductVO productVO = new ProductVO();
                        BeanUtils.copyProperties(product, productVO);
                        productVO.setStock(getStockByProductId(productVO.getId()));

                        vo.setProduct(productVO);
                        products.add(vo);
                    }
                    pager.setTotal(resultEntity.getData().getTotal());
                    pager.setPageNum(resultEntity.getData().getPageNum());
                    pager.setPageSize(resultEntity.getData().getPageSize());
                    pager.setContent(products);
                    return pager;
                }
            }
        } else {
            logger.error(resultEntity.getMessage());
        }
        return null;
    }

    private List<EsProduct> getProductListByQueryParam(EsProduct queryParam) {
        EsProductExample example = new EsProductExample();
        EsProductExample.Criteria criteria = example.createCriteria();
        if (null != queryParam.getName()) {
            criteria.andNameEqualTo(queryParam.getName());
        }
        if (null != queryParam.getId()) {
            criteria.andIdEqualTo(queryParam.getId());
        }
        if (null != queryParam.getItemNo()) {
            criteria.andItemNoEqualTo(queryParam.getItemNo());
        }
        return productMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 通过商品id获取库存（sku库存综合）
     */
    private Integer getStockByProductId(Long productId) {

        int sum = 0;

        EsSkuProductExample example = new EsSkuProductExample();
        EsSkuProductExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(productId);

        List<EsSkuProduct> skuProducts = skuProductMapper.selectByExample(example);

        for (EsSkuProduct skuProduct : skuProducts) {
            sum += skuProduct.getStock();
        }

        return sum;

    }

    /**
     * 删除sku_spec_detail关系
     */
    private void deleteSkuSpecDetails(List<EsSkuProduct> skuProducts) {
        for (EsSkuProduct skuProduct : skuProducts) {
            // 删除旧的sku和spec关系
            EsSkuSpecDetailExample example1 = new EsSkuSpecDetailExample();
            EsSkuSpecDetailExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andSkuIdEqualTo(skuProduct.getId());
            skuSpecDetailMapper.deleteByExample(example1);
        }
    }

    /**
     * 插入sku和spec关系
     */
    private void insertSkuSpec(SkuProductDTO skuProductDTO, EsSkuProduct skuProduct) {
        List<Long> specDetailIds = skuProductDTO.getSpecDetails();
        for (Long specDetailId : specDetailIds) {
            EsSkuSpecDetail skuSpec = new EsSkuSpecDetail();
            skuSpec.setSkuId(skuProduct.getId());
            skuSpec.setSpecDetailId(specDetailId);
            skuSpec.setCreateTime(new Date());
            skuSpecDetailMapper.insert(skuSpec);
        }
    }

    /**
     * 整合sku商品规格详情名称
     */
    private String integrationName(SkuProductDTO skuProductDTO) {
        StringBuilder builder = new StringBuilder();
        List<Long> specDetailIds = skuProductDTO.getSpecDetails();
        for (int i = 0; i < specDetailIds.size(); i++) {
            EsProductSpecDetail productSpecDetail = productSpecDetailMapper.selectByPrimaryKey(specDetailIds.get(i));
            if (i == specDetailIds.size() - 1) {
                builder.append(productSpecDetail.getName());
            } else {
                builder.append(productSpecDetail.getName()).append("/");
            }

        }
        return builder.toString();
    }

}

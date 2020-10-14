package com.pytap.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.api.model.dto.FlashSaleDTO;
import com.pytap.api.service.api.sale.FlashSaleFeignService;
import com.pytap.api.service.api.sale.NewProductRecommendFeignService;
import com.pytap.common.constant.HomeNumber;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.dao.EsProductMapper;
import com.pytap.generator.entity.*;
import com.pytap.product.service.HomeProductService;
import com.pytap.product.service.ProductCategoryService;
import com.pytap.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Ecin520
 * @date 2020/10/13 10:36
 */
@Service
public class HomeProductServiceImpl implements HomeProductService {

    private static final Logger logger = LoggerFactory.getLogger(HomeProductServiceImpl.class);

    @Resource
    private ProductService productService;

    @Resource
    private EsProductMapper productMapper;

    @Resource
    private ProductCategoryService productCategoryService;

    @Resource
    private FlashSaleFeignService flashSaleFeignService;

    @Resource
    private NewProductRecommendFeignService newProductRecommendFeignService;

    @Override
    public Pager<EsProduct> listHomeFlashSaleProducts(QueryParam<FlashSaleDTO> queryParam) {
        Pager<EsProduct> pager = new Pager<>();
        List<EsProduct> products = new ArrayList<>(16);

        ResultEntity<Pager<EsFlashSaleProduct>> resultEntity = flashSaleFeignService.listFlashSaleProductsByQueryParam(queryParam);

        if (200 == resultEntity.getCode()) {
            if (null != resultEntity.getData()) {
                if (null != resultEntity.getData().getContent()) {
                    List<EsFlashSaleProduct> list = resultEntity.getData().getContent();
                    for (EsFlashSaleProduct flashSaleProduct : list) {
                        EsProduct product = new EsProduct();
                        product.setId(flashSaleProduct.getProductId());
                        products.add(productService.getProduct(product));
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

    @Override
    public Pager<EsProduct> listHomeNewProducts(Integer pageNum, Integer pageSize) {
        Pager<EsProduct> pager = new Pager<>();
        List<EsProduct> products = new ArrayList<>(16);

        ResultEntity<Pager<EsNewProductRecommend>> resultEntity = newProductRecommendFeignService.listNewProductsRecommend(pageNum, pageSize);

        if (200 == resultEntity.getCode()) {
            if (null != resultEntity.getData()) {
                if (null != resultEntity.getData().getContent()) {
                    List<EsNewProductRecommend> list = resultEntity.getData().getContent();
                    for (EsNewProductRecommend newProductRecommend : list) {
                        EsProduct product = new EsProduct();
                        product.setId(newProductRecommend.getProductId());
                        products.add(productService.getProduct(product));
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

    /**
     * 在这里随机选出商品，先不写推荐算法
     * */
    @Override
    public Pager<EsProduct> listHomeRecommendProducts(Integer pageNum, Integer pageSize) {

        Pager<EsProduct> pager = new Pager<>();

        EsProductExample example = new EsProductExample();

        PageHelper.startPage(pageNum, pageSize);
        List<EsProduct> products = productMapper.selectByExample(example);
        pager.setTotal(productMapper.countByExample(null));
        pager.setPageNum(pageNum);
        pager.setPageSize(pageSize);
        pager.setContent(products);

        return pager;
    }

    /**
     * 随机商品
     * */
    @Override
    public List<EsProduct> listHomeCarouselProducts() {

        Set<EsProduct> set = new HashSet<>(16);

        int productCount = productMapper.countByExample(null);

        for (int i = 0; i < HomeNumber.HOME_CAROUSEL_COUNT; i++) {
            int rand = (int) (Math.random() * productCount + 0);
            PageHelper.startPage(rand, 1);
            List<EsProduct> list = productMapper.selectByExample(null);
            if (!list.isEmpty()) {
                set.add(list.get(0));
            }
        }

        return new ArrayList<>(set);
    }

    @Override
    public Pager<EsProductCategory> listHomeFeaturedCategories(Integer pageNum, Integer pageSize) {
        return productCategoryService.listProductCategories(pageNum, pageSize, "recommend");
    }

}

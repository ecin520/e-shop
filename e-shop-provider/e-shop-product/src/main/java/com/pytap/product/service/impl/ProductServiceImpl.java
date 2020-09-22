package com.pytap.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.UUIDUtil;
import com.pytap.generator.dao.EsProductMapper;
import com.pytap.generator.dao.EsProductSpecDetailMapper;
import com.pytap.generator.dao.EsSkuProductMapper;
import com.pytap.generator.dao.EsSkuSpecDetailMapper;
import com.pytap.generator.entity.*;
import com.pytap.product.model.dto.ProductParam;
import com.pytap.product.model.dto.SkuProductParam;
import com.pytap.product.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/9 16:23
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private EsProductMapper productMapper;

    @Resource
    private EsSkuProductMapper skuProductMapper;

    @Resource
    private EsSkuSpecDetailMapper skuSpecDetailMapper;

    @Resource
    private EsProductSpecDetailMapper productSpecDetailMapper;

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
    public EsProduct getProduct(EsProduct queryParam) {
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
        List<EsProduct> list = productMapper.selectByExampleWithBLOBs(example);
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
    public Integer insertProductByParam(ProductParam productParam) {

        // 插入商品
        EsProduct product = new EsProduct();
        BeanUtils.copyProperties(productParam.getProduct(), product);

        product.setCreateTime(new Date());
        product.setVerifyStatus(0);
        product.setItemNo(UUIDUtil.uuidNumberString());
        productMapper.insert(product);

        // 传输对象中获取Sku列表
        List<SkuProductParam> skuProductParamList = productParam.getSkuProductList();
        for (SkuProductParam skuProductParam : skuProductParamList) {

            EsSkuProduct skuProduct = new EsSkuProduct();
            BeanUtils.copyProperties(skuProductParam, skuProduct);

            skuProduct.setName(integrationName(skuProductParam));
            skuProduct.setProductId(product.getId());
            skuProduct.setSale(0);
            skuProduct.setCreateTime(new Date());
            skuProductMapper.insert(skuProduct);

            insertSkuSpec(skuProductParam, skuProduct);
        }

        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updateProductByParam(ProductParam productParam) {

        // 更新商品
        EsProduct product = new EsProduct();
        BeanUtils.copyProperties(productParam.getProduct(), product);
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
        List<SkuProductParam> skuProductParamList = productParam.getSkuProductList();
        for (SkuProductParam skuProductParam : skuProductParamList) {
            // 插入新的sku商品
            EsSkuProduct skuProduct = new EsSkuProduct();
            BeanUtils.copyProperties(skuProductParam, skuProduct);

            skuProduct.setName(integrationName(skuProductParam));
            skuProduct.setProductId(product.getId());
            skuProduct.setUpdateTime(new Date());
            skuProductMapper.insert(skuProduct);

            insertSkuSpec(skuProductParam, skuProduct);
        }

        return 1;
    }

    /**
     * 删除sku_spec_detail关系
     * */
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
     * */
    private void insertSkuSpec(SkuProductParam skuProductParam, EsSkuProduct skuProduct) {
        List<Long> specDetailIds = skuProductParam.getSpecDetails();
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
     * */
    private String integrationName(SkuProductParam skuProductParam) {
        StringBuilder builder = new StringBuilder();
        List<Long> specDetailIds = skuProductParam.getSpecDetails();
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

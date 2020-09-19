package com.pytap.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.UUIDUtil;
import com.pytap.generator.dao.EsProductMapper;
import com.pytap.generator.dao.EsSkuProductMapper;
import com.pytap.generator.entity.EsProduct;
import com.pytap.generator.entity.EsProductExample;
import com.pytap.generator.entity.EsSkuProductExample;
import com.pytap.product.service.ProductService;
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
        // 先删除商品对应的sku
        EsSkuProductExample example = new EsSkuProductExample();
        EsSkuProductExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(id);
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
}

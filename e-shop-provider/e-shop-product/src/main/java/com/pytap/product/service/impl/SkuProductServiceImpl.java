package com.pytap.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.common.utils.Pager;
import com.pytap.generator.dao.EsSkuProductMapper;
import com.pytap.generator.entity.EsSkuProduct;
import com.pytap.generator.entity.EsSkuProductExample;
import com.pytap.product.service.SkuProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/9 16:24
 */
@Service
public class SkuProductServiceImpl implements SkuProductService {

    @Resource
    private EsSkuProductMapper skuProductMapper;

    @Override
    public Integer insertSkuProduct(EsSkuProduct skuProduct) {
        skuProduct.setCreateTime(new Date());
        return skuProductMapper.insert(skuProduct);
    }

    @Override
    public Integer deleteSkuProductById(Long id) {
        return skuProductMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateSkuProduct(EsSkuProduct skuProduct) {
        skuProduct.setUpdateTime(new Date());
        return skuProductMapper.updateByPrimaryKeySelective(skuProduct);
    }

    @Override
    public EsSkuProduct getSkuProduct(EsSkuProduct queryParam) {
        EsSkuProductExample example = new EsSkuProductExample();
        EsSkuProductExample.Criteria criteria = example.createCriteria();
        if (null != queryParam.getName()) {
            criteria.andNameEqualTo(queryParam.getName());
        }
        if (null != queryParam.getId()) {
            criteria.andIdEqualTo(queryParam.getId());
        }
        List<EsSkuProduct> list = skuProductMapper.selectByExample(example);
        return !list.isEmpty() ? list.get(0) : null;
    }

    @Override
    public Pager<EsSkuProduct> listSkuProducts(Integer pageNum, Integer pageSize, EsSkuProduct queryParam) {
        PageHelper.startPage(pageNum, pageSize);
        EsSkuProductExample example = new EsSkuProductExample();
        EsSkuProductExample.Criteria criteria = example.createCriteria();

        // sku商品名称模糊搜索
        if (null != queryParam.getName()) {
            criteria.andNameLike("%" + queryParam.getName() + "%");
        }

        // spu搜索sku
        if (null != queryParam.getProductId()) {
            criteria.andProductIdEqualTo(queryParam.getProductId());
        }

        // 通过规格id搜索商品
        if (null != queryParam.getProductSpecDetailId()) {
            criteria.andProductSpecDetailIdEqualTo(queryParam.getProductSpecDetailId());
        }

        List<EsSkuProduct> list = skuProductMapper.selectByExample(example);
        Pager<EsSkuProduct> pager = new Pager<>();
        pager.setPageNum(pageNum);
        pager.setPageSize(pageSize);
        pager.setContent(list);
        pager.setTotal(skuProductMapper.countByExample(example));
        return pager;
    }


}

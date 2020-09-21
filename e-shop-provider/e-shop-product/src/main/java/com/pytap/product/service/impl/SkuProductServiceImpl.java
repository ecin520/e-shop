package com.pytap.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.SortUtil;
import com.pytap.generator.dao.EsSkuProductMapper;
import com.pytap.generator.dao.EsSkuSpecDetailMapper;
import com.pytap.generator.entity.EsSkuProduct;
import com.pytap.generator.entity.EsSkuProductExample;
import com.pytap.generator.entity.EsSkuSpecDetail;
import com.pytap.generator.entity.EsSkuSpecDetailExample;
import com.pytap.product.service.SkuProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
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

    @Resource
    private EsSkuSpecDetailMapper skuSpecDetailMapper;

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

        List<EsSkuProduct> list = skuProductMapper.selectByExample(example);
        Pager<EsSkuProduct> pager = new Pager<>();
        pager.setPageNum(pageNum);
        pager.setPageSize(pageSize);
        pager.setContent(list);
        pager.setTotal(skuProductMapper.countByExample(example));
        return pager;
    }

    @Override
    public EsSkuProduct getSkuProductByParam(Long productId, List<Long> specDetailIds) {
        EsSkuProductExample skuProductExample = new EsSkuProductExample();
        EsSkuProductExample.Criteria criteria = skuProductExample.createCriteria();
        criteria.andProductIdEqualTo(productId);
        List<EsSkuProduct> skuProducts = skuProductMapper.selectByExample(skuProductExample);

        for (EsSkuProduct skuProduct : skuProducts) {
            EsSkuSpecDetailExample skuSpecDetailExample = new EsSkuSpecDetailExample();
            EsSkuSpecDetailExample.Criteria criteria1 = skuSpecDetailExample.createCriteria();
            criteria1.andSkuIdEqualTo(skuProduct.getId());
            List<EsSkuSpecDetail> skuSpecDetails = skuSpecDetailMapper.selectByExample(skuSpecDetailExample);
            List<Long> list = new ArrayList<>(16);
            for (EsSkuSpecDetail esSkuSpecDetail : skuSpecDetails) {
                list.add(esSkuSpecDetail.getSpecDetailId());
            }
            if (SortUtil.compareList(list, specDetailIds)) {
                return skuProduct;
            }
        }
        return null;
    }


}

package com.pytap.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.common.utils.Pager;
import com.pytap.generator.dao.EsProductCategorySpecMapper;
import com.pytap.generator.dao.EsProductSpecDetailMapper;
import com.pytap.generator.dao.EsProductSpecMapper;
import com.pytap.generator.entity.*;
import com.pytap.product.service.ProductSpecService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/9 16:22
 */
@Service
public class ProductSpecServiceImpl implements ProductSpecService {

    @Resource
    private EsProductSpecMapper productSpecMapper;

    @Resource
    private EsProductSpecDetailMapper productSpecDetailMapper;

    @Resource
    private EsProductCategorySpecMapper productCategorySpecMapper;


    @Override
    public Integer insertProductSpec(EsProductSpec productSpec) {
        productSpec.setCreateTime(new Date());
        if (null == productSpec.getStandard()) {
            productSpec.setStandard(0);
        }
        return productSpecMapper.insert(productSpec);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer deleteProductSpecById(Long id) {
        // 先删除规格详情
        EsProductSpecDetailExample example = new EsProductSpecDetailExample();
        EsProductSpecDetailExample.Criteria criteria = example.createCriteria();
        criteria.andProductSpecIdEqualTo(id);
        productSpecDetailMapper.deleteByExample(example);

        // 删除分类规格关系表中的关系(拥有此规格的数据全部删除，否则数据会混乱)
        EsProductCategorySpecExample relateExample = new EsProductCategorySpecExample();
        EsProductCategorySpecExample.Criteria relateCriteria = relateExample.createCriteria();
        relateCriteria.andProductSpecIdEqualTo(id);
        productCategorySpecMapper.deleteByExample(relateExample);

        // 删除规格
        productSpecMapper.deleteByPrimaryKey(id);

        return 1;
    }

    @Override
    public Integer updateProductSpec(EsProductSpec productSpec) {
        productSpec.setUpdateTime(new Date());
        return productSpecMapper.updateByPrimaryKeySelective(productSpec);
    }

    @Override
    public EsProductSpec getProductSpec(EsProductSpec queryParam) {
        EsProductSpecExample example = new EsProductSpecExample();
        EsProductSpecExample.Criteria criteria = example.createCriteria();
        if (null != queryParam.getId()) {
            criteria.andIdEqualTo(queryParam.getId());
        }
        if (null != queryParam.getName()) {
            criteria.andNameEqualTo(queryParam.getName());
        }
        List<EsProductSpec> list = productSpecMapper.selectByExample(example);
        return !list.isEmpty() ? list.get(0) : null;
    }

    @Override
    public Pager<EsProductSpec> listProductSpecs(Integer pageNum, Integer pageSize, EsProductSpec queryParam) {

        if (null != pageNum && null != pageSize) {
            PageHelper.startPage(pageNum, pageSize);
        } else {
            PageHelper.startPage(0, 0);
        }

        EsProductSpecExample example = new EsProductSpecExample();
        EsProductSpecExample.Criteria criteria = example.createCriteria();

        if (null != queryParam) {

            if (!StringUtils.isEmpty(queryParam.getName())) {
                criteria.andNameLike("%" + queryParam.getName() + "%");
            }
            if (null != queryParam.getStandard()) {
                criteria.andStandardEqualTo(queryParam.getStandard());
            }
        }

        List<EsProductSpec> list = productSpecMapper.selectByExample(example);
        Pager<EsProductSpec> pager = new Pager<>();
        pager.setPageNum(pageNum);
        pager.setPageSize(pageSize);
        pager.setContent(list);
        pager.setTotal(productSpecMapper.countByExample(example));
        return pager;
    }

    @Override
    public Integer insertProductSpecDetail(EsProductSpecDetail productSpecDetail) {
        if (null == productSpecDetail.getStandard()) {
            productSpecDetail.setStandard(0);
        }
        productSpecDetail.setCreateTime(new Date());
        return productSpecDetailMapper.insert(productSpecDetail);
    }

    @Override
    public Integer deleteProductSpecDetailById(Long id) {
        return productSpecDetailMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateProductSpecDetail(EsProductSpecDetail productSpecDetail) {
        productSpecDetail.setUpdateTime(new Date());
        return productSpecDetailMapper.updateByPrimaryKeySelective(productSpecDetail);
    }

    @Override
    public EsProductSpecDetail getProductSpecDetail(EsProductSpecDetail queryParam) {
        EsProductSpecDetailExample example = new EsProductSpecDetailExample();
        EsProductSpecDetailExample.Criteria criteria = example.createCriteria();
        if (null != queryParam.getId()) {
            criteria.andIdEqualTo(queryParam.getId());
        }
        if (null != queryParam.getName()) {
            criteria.andNameEqualTo(queryParam.getName());
        }
        List<EsProductSpecDetail> list = productSpecDetailMapper.selectByExample(example);
        return !list.isEmpty() ? list.get(0) : null;
    }

    @Override
    public Pager<EsProductSpecDetail> listProductSpecDetails(Integer pageNum, Integer pageSize, Long productSpecId) {
        PageHelper.startPage(pageNum, pageSize);
        EsProductSpecDetailExample example = new EsProductSpecDetailExample();
        EsProductSpecDetailExample.Criteria criteria = example.createCriteria();
        criteria.andProductSpecIdEqualTo(productSpecId);
        List<EsProductSpecDetail> list = productSpecDetailMapper.selectByExample(example);
        Pager<EsProductSpecDetail> pager = new Pager<>();
        pager.setPageNum(pageNum);
        pager.setPageSize(pageSize);
        pager.setContent(list);
        pager.setTotal(productSpecDetailMapper.countByExample(example));
        return pager;
    }
}

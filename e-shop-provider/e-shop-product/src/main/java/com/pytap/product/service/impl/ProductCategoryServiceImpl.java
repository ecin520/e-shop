package com.pytap.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.pytap.common.utils.Pager;
import com.pytap.generator.dao.EsProductCategoryDetailMapper;
import com.pytap.generator.dao.EsProductCategoryMapper;
import com.pytap.generator.entity.EsProductCategory;
import com.pytap.generator.entity.EsProductCategoryDetail;
import com.pytap.generator.entity.EsProductCategoryDetailExample;
import com.pytap.generator.entity.EsProductCategoryExample;
import com.pytap.product.service.ProductCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/9 15:54
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Resource
    private EsProductCategoryMapper productCategoryMapper;

    @Resource
    private EsProductCategoryDetailMapper productCategoryDetailMapper;

    @Override
    public Integer insertProductCategory(EsProductCategory productCategory) {
        productCategory.setCreateTime(new Date());
        productCategory.setShowStatus(1);
        productCategory.setCount(0);
        return productCategoryMapper.insert(productCategory);
    }

    @Transactional
    @Override
    public Integer deleteProductCategoryById(Long id) {
        // 先删除分类的下属详情
        EsProductCategoryDetailExample example = new EsProductCategoryDetailExample();
        EsProductCategoryDetailExample.Criteria criteria = example.createCriteria();
        criteria.andProductCategoryIdEqualTo(id);
        productCategoryDetailMapper.deleteByExample(example);

        // 删除分类
        return productCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateProductCategory(EsProductCategory productCategory) {
        productCategory.setUpdateTime(new Date());
        return productCategoryMapper.updateByPrimaryKeySelective(productCategory);
    }

    @Override
    public EsProductCategory getProductCategory(EsProductCategory queryParam) {
        EsProductCategoryExample example = new EsProductCategoryExample();
        EsProductCategoryExample.Criteria criteria = example.createCriteria();
        if (null != queryParam.getId()) {
            criteria.andIdEqualTo(queryParam.getId());
        }
        if (null != queryParam.getName()) {
            criteria.andNameEqualTo(queryParam.getName());
        }
        List<EsProductCategory> list = productCategoryMapper.selectByExampleWithBLOBs(example);
        return !list.isEmpty() ? list.get(0) : null;
    }

    @Override
    public Pager<EsProductCategory> listProductCategories(Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        EsProductCategoryExample example = new EsProductCategoryExample();
        EsProductCategoryExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andKeywordsLike("%" + keyword + "%");
            example.or(example.createCriteria().andNameLike("%" + keyword + "%"));
        }
        List<EsProductCategory> list = productCategoryMapper.selectByExampleWithBLOBs(example);
        Pager<EsProductCategory> pager = new Pager<>();
        pager.setPageNum(pageNum);
        pager.setPageSize(pageSize);
        pager.setContent(list);
        pager.setTotal(productCategoryMapper.countByExample(example));
        return pager;
    }

    @Override
    public Integer insertProductCategoryDetail(EsProductCategoryDetail productCategoryDetail) {
        productCategoryDetail.setCreateTime(new Date());
        return productCategoryDetailMapper.insert(productCategoryDetail);
    }

    @Override
    public Integer deleteProductCategoryDetailById(Long id) {
        return productCategoryDetailMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateProductCategoryDetail(EsProductCategoryDetail productCategoryDetail) {
        productCategoryDetail.setUpdateTime(new Date());
        return productCategoryDetailMapper.updateByPrimaryKeySelective(productCategoryDetail);
    }

    @Override
    public EsProductCategoryDetail getProductCategoryDetail(EsProductCategoryDetail queryParam) {
        EsProductCategoryDetailExample example = new EsProductCategoryDetailExample();
        EsProductCategoryDetailExample.Criteria criteria = example.createCriteria();
        if (null != queryParam.getId()) {
            criteria.andIdEqualTo(queryParam.getId());
        }
        if (null != queryParam.getName()) {
            criteria.andNameEqualTo(queryParam.getName());
        }
        List<EsProductCategoryDetail> list = productCategoryDetailMapper.selectByExampleWithBLOBs(example);
        return !list.isEmpty() ? list.get(0) : null;
    }

    @Override
    public List<EsProductCategoryDetail> listProductCategoryDetails() {
        return productCategoryDetailMapper.selectByExampleWithBLOBs(null);
    }

    @Override
    public Pager<EsProductCategoryDetail> listProductCategoryDetailsByCategoryId(Integer pageNum, Integer pageSize, Long categoryId) {
        PageHelper.startPage(pageNum, pageSize);
        EsProductCategoryDetailExample example = new EsProductCategoryDetailExample();
        EsProductCategoryDetailExample.Criteria criteria = example.createCriteria();
        criteria.andProductCategoryIdEqualTo(categoryId);
        return productCategoryDetailPager(pageNum, pageSize, example);
    }

    @Override
    public Pager<EsProductCategoryDetail> listProductCategoryDetails(Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        EsProductCategoryDetailExample example = new EsProductCategoryDetailExample();
        EsProductCategoryDetailExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andNameLike("%" + keyword + "%");
        }
        return productCategoryDetailPager(pageNum, pageSize, example);
    }

    private Pager<EsProductCategoryDetail> productCategoryDetailPager(Integer pageNum, Integer pageSize, EsProductCategoryDetailExample example) {
        List<EsProductCategoryDetail> list = productCategoryDetailMapper.selectByExampleWithBLOBs(example);
        Pager<EsProductCategoryDetail> pager = new Pager<>();
        pager.setPageNum(pageNum);
        pager.setPageSize(pageSize);
        pager.setContent(list);
        pager.setTotal(productCategoryDetailMapper.countByExample(example));
        return pager;
    }
}

package com.pytap.product.service.impl;

import com.pytap.common.utils.Pager;
import com.pytap.generator.dao.EsProductCategoryDetailMapper;
import com.pytap.generator.dao.EsProductCategorySpecMapper;
import com.pytap.generator.dao.EsProductSpecDetailMapper;
import com.pytap.generator.entity.*;
import com.pytap.product.dao.ProductCategorySpecDao;
import com.pytap.product.model.vo.ProductSpecVO;
import com.pytap.product.service.ProductCategorySpecService;
import com.pytap.product.service.ProductSpecService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ecin520
 * @date 2020/9/15 16:45
 */
@Service
public class ProductCategorySpecServiceImpl implements ProductCategorySpecService {

    @Resource
    private EsProductCategorySpecMapper productCategorySpecMapper;

    @Resource
    private EsProductCategoryDetailMapper productCategoryDetailMapper;

    @Resource
    private EsProductSpecDetailMapper productSpecDetailMapper;

    @Resource
    private ProductCategorySpecDao productCategorySpecDao;

    @Resource
    private ProductSpecService productSpecService;

    @Override
    public Integer insertProductCategorySpec(EsProductCategorySpec productCategorySpec) {
        return productCategorySpecMapper.insert(productCategorySpec);
    }

    @Override
    public Integer insertProductCategorySpecs(Long categoryId, List<Long> specIds) {
        return productCategorySpecDao.insertProductCategorySpecs(categoryId, specIds);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updateProductCategorySpecs(Long categoryId, List<Long> productSpecIds) {
        // 先删除原有关系
        EsProductCategorySpecExample example = new EsProductCategorySpecExample();
        EsProductCategorySpecExample.Criteria criteria = example.createCriteria();
        criteria.andProductCategoryIdEqualTo(categoryId);
        productCategorySpecMapper.deleteByExample(example);

        if (productSpecIds.isEmpty()) {
            return 1;
        }

        // 批量插入关系
        productCategorySpecDao.insertProductCategorySpecs(categoryId, productSpecIds);

        return 1;
    }

    @Override
    public Integer deleteProductCategorySpecById(Long id) {
        return productCategorySpecMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateProductCategorySpec(EsProductCategorySpec productCategorySpec) {
        productCategorySpec.setUpdateTime(new Date());
        return productCategorySpecMapper.updateByPrimaryKeySelective(productCategorySpec);
    }

    @Override
    public List<EsProductSpec> listProductSpecsByCategoryId(Long categoryId) {
        return productCategorySpecDao.listProductSpecsByCategoryId(categoryId);
    }

    @Override
    public List<ProductSpecVO> listProductSpecVOsByCategoryId(Long categoryId) {
        List<EsProductSpec> productSpecs = productCategorySpecDao.listProductSpecsByCategoryId(categoryId);
        return getProductSpecVOList(productSpecs);
    }

    @Override
    public List<ProductSpecVO> listProductSpecVOsByCategoryDetailId(Long categoryDetailId) {
        EsProductCategoryDetail productCategoryDetail = productCategoryDetailMapper.selectByPrimaryKey(categoryDetailId);
        List<EsProductSpec> productSpecs = productCategorySpecDao.listProductSpecsByCategoryId(productCategoryDetail.getProductCategoryId());
        return getProductSpecVOList(productSpecs);
    }

    private List<ProductSpecVO> getProductSpecVOList(List<EsProductSpec> productSpecs) {
        List<ProductSpecVO> result = new ArrayList<>(16);
        for (EsProductSpec productSpec : productSpecs) {
            ProductSpecVO vo = new ProductSpecVO();
            BeanUtils.copyProperties(productSpec, vo);
            Pager<EsProductSpecDetail> pager = productSpecService.listProductSpecDetails(0, 0, productSpec.getId());
            if (null != pager.getContent()) {
                vo.setDetails(pager.getContent().stream().filter((item) -> item.getStandard() == 1).collect(Collectors.toList()));
            }
            result.add(vo);
        }
        return result;
    }

    @Override
    public List<ProductSpecVO> listSpecVOsByCategoryAndProductId(Long categoryId, Long productId) {
        List<EsProductSpec> productSpecs = productCategorySpecDao.listProductSpecsByCategoryId(categoryId);
        List<ProductSpecVO> result = new ArrayList<>(16);

        // 遍历分类对应规格，然后通过规格id获取规格详情
        for (EsProductSpec productSpec : productSpecs) {
            ProductSpecVO vo = new ProductSpecVO();
            BeanUtils.copyProperties(productSpec, vo);

            EsProductSpecDetailExample productSpecDetailExample = new EsProductSpecDetailExample();
            EsProductSpecDetailExample.Criteria criteria = productSpecDetailExample.createCriteria();
            criteria.andStandardEqualTo(1);
            criteria.andProductSpecIdEqualTo(productSpec.getId());
            List<EsProductSpecDetail> details = productSpecDetailMapper.selectByExample(productSpecDetailExample);

            if (!details.isEmpty()) {
                vo.setDetails(details);
            }
            result.add(vo);
        }

        // 通过商品id获取规格详情列表,这里查询的是standard为0的用户自定义规格详情
        List<EsProductSpecDetail> productSpecDetails = productCategorySpecDao.listProductSpecDetailsByProductId(0, productId);

        // 添加到结果集
        return addToResult(result, productSpecDetails);
    }



    @Override
    public List<ProductSpecVO> listSpecVOsByCategoryDetailAndProductId(Long categoryDetailId, Long productId) {
        EsProductCategoryDetail productCategoryDetail = productCategoryDetailMapper.selectByPrimaryKey(categoryDetailId);
        List<EsProductSpec> productSpecs = productCategorySpecDao.listProductSpecsByCategoryId(productCategoryDetail.getProductCategoryId());

        List<ProductSpecVO> result = new ArrayList<>(16);

        // 遍历分类对应规格，然后通过规格id获取产品拥有的规格详情
        for (EsProductSpec productSpec : productSpecs) {
            ProductSpecVO vo = new ProductSpecVO();
            BeanUtils.copyProperties(productSpec, vo);
            result.add(vo);
        }

        List<EsProductSpecDetail> productSpecDetails = productCategorySpecDao.listProductOwnSpecDetailsByProductId(productId);

        // 添加到结果集
        return addToResult(result, productSpecDetails);
    }

    private List<ProductSpecVO> addToResult(List<ProductSpecVO> result, List<EsProductSpecDetail> productSpecDetails) {
        // 添加到结果集
        for (EsProductSpecDetail productSpecDetail : productSpecDetails) {
            for (ProductSpecVO vo : result) {
                if (null == vo.getDetails()) {
                    vo.setDetails(new ArrayList<>(16));
                }
                if (productSpecDetail.getProductSpecId().equals(vo.getId())) {
                    vo.getDetails().add(productSpecDetail);
                }
            }
        }
        return result;
    }

    @Override
    public List<EsProductSpecDetail> listSpecDetailsByProductId(Long productId) {
        return productCategorySpecDao.listSpecDetailsByProductId(productId);
    }

}

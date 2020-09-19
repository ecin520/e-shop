package com.pytap.product.service.impl;

import com.pytap.common.utils.Pager;
import com.pytap.generator.dao.EsProductCategorySpecMapper;
import com.pytap.generator.entity.*;
import com.pytap.product.dao.ProductCategorySpecDao;
import com.pytap.product.model.dto.ProductSpecDTO;
import com.pytap.product.service.ProductCategorySpecService;
import com.pytap.product.service.ProductSpecService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/15 16:45
 */
@Service
public class ProductCategorySpecServiceImpl implements ProductCategorySpecService {

    @Resource
    private EsProductCategorySpecMapper productCategorySpecMapper;

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
    public List<ProductSpecDTO> listProductSpecDTOsByCategoryId(Long categoryId) {
        List<ProductSpecDTO> result = new ArrayList<>(16);
        List<EsProductSpec> productSpecs = productCategorySpecDao.listProductSpecsByCategoryId(categoryId);
        for (EsProductSpec productSpec : productSpecs) {
            ProductSpecDTO dto = new ProductSpecDTO();
            System.out.println(productSpec.toString());
            BeanUtils.copyProperties(productSpec, dto);
            Pager<EsProductSpecDetail> pager = productSpecService.listProductSpecDetails(0, 0, productSpec.getId());
            if (null != pager.getContent()) {
                dto.setDetails(pager.getContent());
            }
            result.add(dto);
        }
        return result;
    }
}

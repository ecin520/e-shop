package com.pytap.product.dao;

import com.pytap.generator.entity.EsProductSpec;
import com.pytap.generator.entity.EsProductSpecDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/15 16:18
 */
@Mapper
public interface ProductCategorySpecDao {

    /**
     * 批量插入分类规格关系,一个分类对应多个规格
     * @param categoryId 分类id
     * @param productSpecIds 规格id列表
     * @return Integer
     * */
    Integer insertProductCategorySpecs(@Param("categoryId") Long categoryId, @Param("list") List<Long> productSpecIds);

    /**
     * 通过分类id获取规格列表
     * @param categoryId 分类id
     * @return List<EsProductSpec>
     * */
    List<EsProductSpec> listProductSpecsByCategoryId(Long categoryId);

    /**
     * 通过standard和产品id获取规格详情列表
     * @param standard 是否标准规格，0 -> 否, 1 -> 是
     * @param productId 产品id
     * @return List<EsProductSpec>
     * */
    List<EsProductSpecDetail> listProductSpecDetailsByProductId(@Param("standard") Integer standard, @Param("productId") Long productId);

    /**
     * 通过产品id获取规格详情列表
     * @param productId 产品id
     * @return List<EsProductSpec>
     * */
    List<EsProductSpecDetail> listProductOwnSpecDetailsByProductId(@Param("productId") Long productId);


    /**
     * 通过产品id获取规格详情列表
     * @param productId 产品id
     * @return List<EsProductSpec>
     * */
    List<EsProductSpecDetail> listSpecDetailsByProductId(@Param("productId") Long productId);

}

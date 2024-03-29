package com.pytap.product.service;

import com.pytap.generator.entity.EsProductCategorySpec;
import com.pytap.generator.entity.EsProductSpec;
import com.pytap.generator.entity.EsProductSpecDetail;
import com.pytap.product.model.vo.ProductSpecVO;

import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/15 16:08
 */
public interface ProductCategorySpecService {
    /**
     * 插入分类规格关系
     * @param productCategorySpec 分类规格关系
     * @return Integer
     * */
    Integer insertProductCategorySpec(EsProductCategorySpec productCategorySpec);

    /**
     * 批量插入分类规格关系,一个分类对应多个规格
     * @param categoryId 分类id
     * @param specIds 规格id列表
     * @return Integer
     * */
    Integer insertProductCategorySpecs(Long categoryId, List<Long> specIds);

    /**
     * 批量更新分类规格关系,一个分类对应多个规格
     * @param categoryId 分类id
     * @param productSpecIds 规格id列表
     * @return Integer
     * */
    Integer updateProductCategorySpecs(Long categoryId, List<Long> productSpecIds);

    /**
     * 主键删除分类规格关系
     * @param id 分类规格关系主键
     * @return Integer
     * */
    Integer deleteProductCategorySpecById(Long id);

    /**
     * 更新分类规格关系
     * @param productCategorySpec 分类规格关系
     * @return Integer
     * */
    Integer updateProductCategorySpec(EsProductCategorySpec productCategorySpec);

    /**
     * 通过分类id获取规格列表
     * @param categoryId 分类id
     * @return List<EsProductSpec>
     * */
    List<EsProductSpec> listProductSpecsByCategoryId(Long categoryId);

    /**
     * 通过分类id获取规格视图列表
     * @param categoryId 分类id
     * @return List<EsProductSpec>
     * */
    List<ProductSpecVO> listProductSpecVOsByCategoryId(Long categoryId);

    /**
     * 通过分类详情id获取规格视图列表
     * @param categoryDetailId 分类详情id
     * @return List<EsProductSpec>
     * */
    List<ProductSpecVO> listProductSpecVOsByCategoryDetailId(Long categoryDetailId);

    /**
     * 通过分类id获取规格视图列表
     * @param categoryId 分类id
     * @param productId 产品id
     * @return List<EsProductSpec>
     * */
    List<ProductSpecVO> listSpecVOsByCategoryAndProductId(Long categoryId, Long productId);

    /**
     * 通过分类详情id获取规格视图列表
     * @param categoryDetailId 分类详情id
     * @param productId 产品id
     * @return List<EsProductSpec>
     * */
    List<ProductSpecVO> listSpecVOsByCategoryDetailAndProductId(Long categoryDetailId, Long productId);

    /**
     * 通过产品id获取规格DTO列表
     * @param productId 产品id
     * @return List<EsProductSpec>
     * */
    List<EsProductSpecDetail> listSpecDetailsByProductId(Long productId);

}

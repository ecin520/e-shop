package com.pytap.product.service;

import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.generator.entity.EsProductCategory;
import com.pytap.generator.entity.EsProductCategoryDetail;
import com.pytap.product.model.vo.ProductCategoryDetailVO;

import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/9 15:54
 */
public interface ProductCategoryService {

    /**
     * 插入产品分类
     * @param productCategory 产品分类
     * @return Integer
     * */
    Integer insertProductCategory(EsProductCategory productCategory);

    /**
     * 主键删除产品分类
     * @param id 产品分类主键
     * @return Integer
     * */
    Integer deleteProductCategoryById(Long id);

    /**
     * 更新产品分类
     * @param productCategory 产品分类
     * @return Integer
     * */
    Integer updateProductCategory(EsProductCategory productCategory);

    /**
     * 获取产品分类
     * @param queryParam 查询参数
     * @return EsProductCategory
     * */
    EsProductCategory getProductCategory(EsProductCategory queryParam);


    /**
     * 列取产品分类
     * @param pageNum 第几页
     * @param pageSize 每页条目数量
     * @param keyword 搜索关键字
     * @return Page<EsProductCategory>
     * */
    Pager<EsProductCategory> listProductCategories(Integer pageNum, Integer pageSize, String keyword);

    /**
     * 插入分类详情
     * @param productCategoryDetail 分类详情
     * @return Integer
     * */
    Integer insertProductCategoryDetail(EsProductCategoryDetail productCategoryDetail);

    /**
     * 主键删除产品分类详情
     * @param id 产品分类详情主键
     * @return Integer
     * */
    Integer deleteProductCategoryDetailById(Long id);

    /**
     * 更新产品分类详情
     * @param productCategoryDetail 产品分类详情
     * @return Integer
     * */
    Integer updateProductCategoryDetail(EsProductCategoryDetail productCategoryDetail);

    /**
     * 获取产品分类详情
     * @param queryParam 查询参数
     * @return EsProductCategoryDetail
     * */
    EsProductCategoryDetail getProductCategoryDetail(EsProductCategoryDetail queryParam);

    /**
     * 通过分类详情id获产品分类VO
     * @param categoryDetailId 分类详情id
     * @return ProductCategoryDetailVO
     * */
    ProductCategoryDetailVO getProductCategoryDetailVOById(Long categoryDetailId);

    /**
     * 列取产品分类详情
     * @return List<EsProductCategoryDetail>
     * */
    List<EsProductCategoryDetail> listProductCategoryDetails();


    /**
     * 查询参数查询分类详情
     * @param queryParam 查询参数
     * @return Pager<EsProductCategoryDetail>
     * */
    Pager<EsProductCategoryDetail> listProductCategoryDetailsByParam(QueryParam<EsProductCategoryDetail> queryParam);

}

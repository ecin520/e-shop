package com.pytap.sale.service;

import com.pytap.common.utils.Pager;
import com.pytap.generator.entity.EsNewProductRecommend;

/**
 * @author Ecin520
 * @date 2020/10/13 11:56
 */
public interface NewProductRecommendService {

    /**
     * 插入新品推荐
     * @param newProductRecommend 新品推荐实体类
     * @return Integer
     * */
    Integer insertNewProductRecommend(EsNewProductRecommend newProductRecommend);

    /**
     * 删除新品推荐
     * @param id 新品推荐参数
     * @return Integer
     * */
    Integer deleteNewProductRecommendById(Long id);

    /**
     * 更新新品推荐
     * @param newProductRecommend 新品推荐参数
     * @return Integer
     * */
    Integer updateNewProductRecommend(EsNewProductRecommend newProductRecommend);

    /**
     * 获取新品列表
     * @param pageNum 第几页
     * @param pageSize 每页大小
     * @return Pager<EsFlashSaleProduct>
     * */
    Pager<EsNewProductRecommend> listNewProductsRecommend(Integer pageNum, Integer pageSize);



}

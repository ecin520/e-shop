package com.pytap.sale.service;

import com.pytap.common.utils.Pager;
import com.pytap.generator.entity.EsFlashSaleProduct;
import com.pytap.generator.entity.EsNewProductRecommend;

/**
 * @author Ecin520
 * @date 2020/10/13 11:56
 */
public interface NewProductRecommendService {
    /**
     * 获取新品列表
     * @param pageNum 第几页
     * @param pageSize 每页大小
     * @return Pager<EsFlashSaleProduct>
     * */
    Pager<EsNewProductRecommend> listNewProductsRecommend(Integer pageNum, Integer pageSize);
}

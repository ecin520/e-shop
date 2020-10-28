package com.pytap.sale.service;

import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.generator.entity.EsProductCarousel;

/**
 * @author Ecin520
 * @date 2020/10/20 10:13
 */
public interface CarouselService {
    /**
     * 插入轮播图商品
     * @param productCarousel 轮播图商品实体类
     * @return Integer
     * */
    Integer insertCarousel(EsProductCarousel productCarousel);

    /**
     * 删除轮播图商品
     * @param param 轮播图商品参数
     * @return Integer
     * */
    Integer deleteProductCarouselByParam(EsProductCarousel param);

    /**
     * 更新轮播图商品
     * @param productCarousel 轮播图商品参数
     * @return Integer
     * */
    Integer updateProductCarousel(EsProductCarousel productCarousel);

    /**
     * 查询参数查询轮播图列表
     * @param queryParam 查询参数，包括分页信息
     * @return Pager<EsProductCarousel>
     * */
    Pager<EsProductCarousel> listProductCarousels(QueryParam<EsProductCarousel> queryParam);

}

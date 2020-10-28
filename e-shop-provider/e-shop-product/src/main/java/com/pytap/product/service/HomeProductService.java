package com.pytap.product.service;

import com.pytap.api.model.dto.FlashSaleDTO;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.generator.entity.EsProduct;
import com.pytap.generator.entity.EsProductCategory;
import com.pytap.product.model.vo.CarouselProductVO;
import com.pytap.product.model.vo.FlashSaleProductVO;

/**
 * @author Ecin520
 * @date 2020/10/13 10:36
 */
public interface HomeProductService {

    /**
     * 获取首页秒杀商品列表
     * @param queryParam 查询参数
     * @return Pager<FlashSaleProductVO>
     * */
    Pager<FlashSaleProductVO> listHomeFlashSaleProducts(QueryParam<FlashSaleDTO> queryParam);

    /**
     * 获取首页新品列表
     * @param pageNum 第几页
     * @param pageSize 每页条目数量
     * @return Pager<EsProduct>
     * */
    Pager<EsProduct> listHomeNewProducts(Integer pageNum, Integer pageSize);

    /**
     * 获取首页推荐商品列表
     * @param pageNum 第几页
     * @param pageSize 每页条目数量
     * @return Pager<EsProduct>
     * */
    Pager<EsProduct> listHomeRecommendProducts(Integer pageNum, Integer pageSize);


    /**
     * 获取首页轮播图列表
     * @return Pager<CarouselProductVO>
     * */
    Pager<CarouselProductVO> listHomeCarouselProducts();

    /**
     * 获取首页精选分类列表
     * @param pageNum 第几页
     * @param pageSize 每页条目数量
     * @return Pager<EsProductCategory>
     * */
    Pager<EsProductCategory> listHomeFeaturedCategories(Integer pageNum, Integer pageSize);
}

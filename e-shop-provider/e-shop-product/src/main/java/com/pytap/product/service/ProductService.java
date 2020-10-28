package com.pytap.product.service;

import com.pytap.api.model.dto.FlashSaleDTO;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.generator.entity.EsProduct;
import com.pytap.generator.entity.EsProductCarousel;
import com.pytap.product.model.dto.ProductDTO;
import com.pytap.product.model.vo.*;

/**
 * @author Ecin520
 * @date 2020/9/9 16:22
 */
public interface ProductService {
    /**
     * 插入商品
     * @param product 商品
     * @return Integer
     * */
    Integer insertProduct(EsProduct product);

    /**
     * 主键删除商品
     * @param id 商品主键
     * @return Integer
     * */
    Integer deleteProductById(Long id);

    /**
     * 更新商品
     * @param product 商品
     * @return Integer
     * */
    Integer updateProduct(EsProduct product);

    /**
     * 获取商品
     * @param queryParam 查询参数
     * @return EsProduct
     * */
    EsProduct getProduct(EsProduct queryParam);

    /**
     * 获取商品
     * @param queryParam 查询参数
     * @return ProductVO
     * */
    ProductVO getProductVO(EsProduct queryParam);

    /**
     * 获取商品
     * @param queryParam 查询参数
     * @return ProductVO
     * */
    ProductWebVO getProductWebVO(EsProduct queryParam);

    /**
     * 列取商品
     * @param pageNum 第几页
     * @param pageSize 每页条目数量
     * @param queryParam 查询参数
     * @return Pager<EsProduct>
     * */
    Pager<EsProduct> listProducts(Integer pageNum, Integer pageSize, EsProduct queryParam);

    /**
     * 列取商品
     * @param productDTO 商品参数传输对象
     * @return Integer
     * */
    Integer insertProductByParam(ProductDTO productDTO);

    /**
     * 更新商品
     * @param productDTO 商品参数传输对象
     * @return Integer
     * */
    Integer updateProductByParam(ProductDTO productDTO);


    /**
     * 获取有效秒杀商品列表
     * @param queryParam 查询参数
     * @return Pager<FlashSaleProductVO>
     * */
    Pager<FlashSaleProductVO> listValidFlashSaleProducts(QueryParam<FlashSaleDTO> queryParam);

    /**
     * 获取秒杀商品列表
     * @param queryParam 查询参数
     * @return Pager<FlashSaleProductVO>
     * */
    Pager<FlashSaleProductVO> listFlashSaleProducts(QueryParam<FlashSaleDTO> queryParam);

    /**
     * 获取新品推荐商品列表
     * @param pageNum 第几页
     * @param pageSize 每页数量
     * @return Pager<FlashSaleProductVO>
     * */
    Pager<NewProductRecommendVO> listNewProductRecommends(Integer pageNum, Integer pageSize);

    /**
     * 获取首页轮播图列表
     * @param queryParam 查询参数
     * @return Pager<CarouselProductVO>
     * */
    Pager<CarouselProductVO> listCarouselProducts(QueryParam<EsProductCarousel> queryParam);

}

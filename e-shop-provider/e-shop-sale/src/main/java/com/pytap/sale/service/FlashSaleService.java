package com.pytap.sale.service;

import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.generator.entity.EsFlashSaleProduct;
import com.pytap.sale.model.dto.FlashSaleDTO;

/**
 * @author Ecin520
 * @date 2020/10/13 11:01
 */
public interface FlashSaleService {

    /**
     * 插入秒杀商品
     * @param flashSaleProduct 秒杀商品实体类
     * @return Integer
     * */
    Integer insertFlashSale(EsFlashSaleProduct flashSaleProduct);

    /**
     * 删除秒杀商品
     * @param param 秒杀商品参数
     * @return Integer
     * */
    Integer deleteFlashSaleByParam(FlashSaleDTO param);

    /**
     * 更新秒杀商品
     * @param flashSaleProduct 秒杀商品参数
     * @return Integer
     * */
    Integer updateFlashSale(EsFlashSaleProduct flashSaleProduct);

    /**
     * 参数获取秒杀商品列表
     * @param queryParam 分页查询参数
     * @return Pager<EsFlashSaleProduct>
     * */
    Pager<EsFlashSaleProduct> listFlashSaleProductsByQueryParam(QueryParam<FlashSaleDTO> queryParam);

}

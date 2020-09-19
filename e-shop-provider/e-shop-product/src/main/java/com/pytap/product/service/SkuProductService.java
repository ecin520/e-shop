package com.pytap.product.service;

import com.pytap.common.utils.Pager;
import com.pytap.generator.entity.EsSkuProduct;

/**
 * @author Ecin520
 * @date 2020/9/9 16:24
 */
public interface SkuProductService {
    /**
     * 插入sku商品
     * @param skuProduct sku商品
     * @return Integer
     * */
    Integer insertSkuProduct(EsSkuProduct skuProduct);

    /**
     * 主键删除sku商品
     * @param id sku商品主键
     * @return Integer
     * */
    Integer deleteSkuProductById(Long id);

    /**
     * 更新sku商品
     * @param skuProduct sku商品
     * @return Integer
     * */
    Integer updateSkuProduct(EsSkuProduct skuProduct);

    /**
     * 获取sku商品
     * @param queryParam 查询参数
     * @return EsSkuProduct
     * */
    EsSkuProduct getSkuProduct(EsSkuProduct queryParam);

    /**
     * 列取sku商品
     * @param pageNum 第几页
     * @param pageSize 每页条目数量
     * @param queryParam 搜素参数
     * @return Page<EsSkuProduct>
     * */
    Pager<EsSkuProduct> listSkuProducts(Integer pageNum, Integer pageSize, EsSkuProduct queryParam);
}

package com.pytap.product.service;

import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.generator.entity.EsProduct;
import com.pytap.generator.entity.EsSkuProduct;

import java.util.List;

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
     * 批量更新sku商品
     * @param skuProductList sku商品列表
     * @return Integer
     * */
    Integer updateSkuProductList(List<EsSkuProduct> skuProductList);

    /**
     * 获取sku商品
     * @param queryParam 查询参数
     * @return EsSkuProduct
     * */
    EsSkuProduct getSkuProduct(EsSkuProduct queryParam);

    /**
     * 列取sku商品
     * @param queryParam 查询参数
     * @return Page<EsSkuProduct>
     * */
    Pager<EsSkuProduct> listSkuProducts(QueryParam<EsSkuProduct> queryParam);

    /**
     * 通过商品id和规格详情获取sku，比如点击两个规格，颜色：红色，大小：42，传入的就是两个选中的规格，这里必须要是完整规格
     * @param productId 商品id
     * @param specDetailIds 规格id列表，存主键
     * @return EsSkuProduct
     * */
    EsSkuProduct getSkuProductByParam(Long productId, List<Long> specDetailIds);

    /**
     * 减少商品库存
     * @param id 商品sku id
     * @return Integer
     * */
    Integer reduceSkuProductStock(Long id);

    /**
     * 增加商品库存
     * @param id 商品sku id
     * @return Integer
     * */
    Integer increaseSkuProductStock(Long id);

}

package com.pytap.product.service;

import com.pytap.common.utils.Pager;
import com.pytap.generator.entity.EsProduct;
import com.pytap.product.model.dto.ProductParam;

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
     * 列取商品
     * @param pageNum 第几页
     * @param pageSize 每页条目数量
     * @param queryParam 查询参数
     * @return Pager<EsProduct>
     * */
    Pager<EsProduct> listProducts(Integer pageNum, Integer pageSize, EsProduct queryParam);

    /**
     * 列取商品
     * @param productParam 商品参数传输对象
     * @return Integer
     * */
    Integer insertProductByParam(ProductParam productParam);

    /**
     * 更新商品
     * @param productParam 商品参数传输对象
     * @return Integer
     * */
    Integer updateProductByParam(ProductParam productParam);



}

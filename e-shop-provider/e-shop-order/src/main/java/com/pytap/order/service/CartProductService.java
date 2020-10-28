package com.pytap.order.service;

import com.pytap.generator.entity.EsCartProduct;

import java.util.List;

/**
 * @author Ecin520
 * @date 2020/10/26 16:59
 */
public interface CartProductService {

    Integer insertCartProduct(EsCartProduct cartProduct);

    Integer deleteCartProduct(EsCartProduct param);

    Integer updateCartProduct(EsCartProduct cartProduct);

    List<EsCartProduct> listCartProducts(EsCartProduct queryParam);

}

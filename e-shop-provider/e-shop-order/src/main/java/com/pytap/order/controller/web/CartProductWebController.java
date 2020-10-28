package com.pytap.order.controller.web;

import com.alibaba.fastjson.JSONObject;
import com.pytap.common.annotation.Log;
import com.pytap.common.utils.ResultEntity;
import com.pytap.common.utils.SecretUtil;
import com.pytap.generator.entity.EsCartProduct;
import com.pytap.order.service.CartProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/10/27 14:47
 */
@RequestMapping("/web/cart")
@RestController
public class CartProductWebController {

    @Resource
    private CartProductService cartProductService;

    @Log(value = "添加商品到购物车")
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ResultEntity<Object> insertCartProduct(@RequestBody String param) {
        EsCartProduct cartProduct = JSONObject.parseObject(SecretUtil.decrypt(param), EsCartProduct.class);
        return 1 != cartProductService.insertCartProduct(cartProduct) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "删除购物车商品")
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ResultEntity<Object> deleteCartProduct(@RequestBody String param) {
        EsCartProduct cartProduct = JSONObject.parseObject(SecretUtil.decrypt(param), EsCartProduct.class);
        return 1 != cartProductService.deleteCartProduct(cartProduct) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "更新购物车商品")
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResultEntity<Object> updateCartProduct(@RequestBody String param) {
        EsCartProduct cartProduct = JSONObject.parseObject(SecretUtil.decrypt(param), EsCartProduct.class);
        return 1 != cartProductService.updateCartProduct(cartProduct) ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "获取购物车商品")
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public ResultEntity<List<EsCartProduct>> listCartProducts(@RequestBody String queryParam) {
        EsCartProduct cartProduct = JSONObject.parseObject(SecretUtil.decrypt(queryParam), EsCartProduct.class);
        return ResultEntity.success(cartProductService.listCartProducts(cartProduct));
    }
}

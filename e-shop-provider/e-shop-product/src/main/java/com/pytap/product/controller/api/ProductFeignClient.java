package com.pytap.product.controller.api;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsMember;
import com.pytap.generator.entity.EsProduct;
import com.pytap.generator.entity.EsSkuProduct;
import com.pytap.product.service.ProductService;
import com.pytap.product.service.SkuProductService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/9/25 10:43
 */
@RequestMapping("/feign/product")
@RestController
public class ProductFeignClient {

    @Resource
    private ProductService productService;

    @Resource
    private SkuProductService skuProductService;

    @Log("远程调用商品查询接口")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ResultEntity<EsProduct> getProduct(@RequestBody EsProduct queryParam) {
        return ResultEntity.success(productService.getProduct(queryParam));
    }

    @Log("远程调用商品sku查询接口")
    @RequestMapping(value = "/sku/query", method = RequestMethod.POST)
    public ResultEntity<EsSkuProduct> getSkuProduct(@RequestBody EsSkuProduct queryParam) {
        return ResultEntity.success(skuProductService.getSkuProduct(queryParam));
    }



}

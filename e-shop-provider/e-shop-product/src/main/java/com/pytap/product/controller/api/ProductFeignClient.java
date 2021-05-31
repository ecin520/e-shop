package com.pytap.product.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.pytap.api.model.dto.StockDTO;
import com.pytap.common.annotation.Log;
import com.pytap.common.utils.ResultEntity;
import com.pytap.common.utils.SecretUtil;
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

    @RequestMapping(value = "/sku/reduce", method = RequestMethod.POST)
    public ResultEntity<Object> reduceSkuProductStock(@RequestBody StockDTO stockDTO) {
        return 1 != skuProductService.reduceSkuProductStock(stockDTO) ? ResultEntity.fail("购买失败，库存不足") : ResultEntity.success("购买成功");
    }

    @RequestMapping(value = "/sku/increase", method = RequestMethod.POST)
    public ResultEntity<Object> increaseSkuProductStock(@RequestBody String param) {
        StockDTO stockDTO = JSONObject.parseObject(SecretUtil.decrypt(param), StockDTO.class);
        return 1 != skuProductService.increaseSkuProductStock(stockDTO) ? ResultEntity.fail("库存恢复失败") : ResultEntity.success("库存恢复成功");
    }

}

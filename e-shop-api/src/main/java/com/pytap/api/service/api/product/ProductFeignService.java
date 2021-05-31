package com.pytap.api.service.api.product;

import com.pytap.api.model.dto.StockDTO;
import com.pytap.api.service.hystrix.product.ProductFeignHystrix;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsProduct;
import com.pytap.generator.entity.EsSkuProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ecin520
 * @date 2020/9/25 11:14
 */
@FeignClient(value = "PRODUCT-PROVIDER", fallbackFactory = ProductFeignHystrix.class)
public interface ProductFeignService {

    @RequestMapping(value = "/feign/product/query", method = RequestMethod.POST)
    ResultEntity<EsProduct> getProduct(@RequestBody EsProduct queryParam);

    @RequestMapping(value = "/feign/product/sku/query", method = RequestMethod.POST)
    ResultEntity<EsSkuProduct> getSkuProduct(@RequestBody EsSkuProduct queryParam);

    @RequestMapping(value = "/feign/product/sku/reduce", method = RequestMethod.POST)
    ResultEntity<Object> reduceSkuProductStock(@RequestBody StockDTO stockDTO);

    @RequestMapping(value = "/feign/product/sku/increase", method = RequestMethod.POST)
    ResultEntity<Object> increaseSkuProductStock(@RequestBody String param);

}

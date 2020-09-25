package com.pytap.api.service.api.product;

import com.pytap.api.service.hystrix.product.ShopFeignHystrix;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsShop;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ecin520
 * @date 2020/9/25 12:40
 */
@FeignClient(value = "PRODUCT-PROVIDER", fallbackFactory = ShopFeignHystrix.class)
public interface ShopFeignService {

    @RequestMapping(value = "/feign/shop/query", method = RequestMethod.POST)
    ResultEntity<EsShop> getShop(@RequestBody EsShop queryParam);

}

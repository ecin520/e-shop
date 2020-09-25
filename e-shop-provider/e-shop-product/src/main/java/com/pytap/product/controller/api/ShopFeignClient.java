package com.pytap.product.controller.api;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsProduct;
import com.pytap.generator.entity.EsShop;
import com.pytap.product.service.ShopService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/9/25 12:35
 */
@RequestMapping("/feign/shop")
@RestController
public class ShopFeignClient {

    @Resource
    private ShopService shopService;

    @Log("远程调用店铺查询接口")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ResultEntity<EsShop> getShop(@RequestBody EsShop queryParam) {
        return ResultEntity.success(shopService.getShop(queryParam));
    }

}

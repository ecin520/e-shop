package com.pytap.product.controller.web;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsShop;
import com.pytap.product.service.ShopService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/10/21 10:35
 */
@RequestMapping("/web/shop")
@RestController
public class ShopWebController {

    @Resource
    private ShopService shopService;

    @Log(value = "获取店铺信息")
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public ResultEntity<EsShop> getShopByParam(@RequestBody EsShop queryParam) {
        return ResultEntity.success(shopService.getShop(queryParam));
    }

}

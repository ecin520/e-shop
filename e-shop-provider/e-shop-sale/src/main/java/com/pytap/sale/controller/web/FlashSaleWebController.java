package com.pytap.sale.controller.web;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsFlashSaleProduct;
import com.pytap.sale.service.FlashSaleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/10/21 10:01
 */
@RequestMapping("/web/flash_sale")
@RestController
public class FlashSaleWebController {

    @Resource
    private FlashSaleService flashSaleService;

    @Log(value = "通过商品id获取秒杀商品列表")
    @RequestMapping(value = "/list/{productId}", method = RequestMethod.GET)
    public ResultEntity<List<EsFlashSaleProduct>> listFlashSaleProductsByProductId(@PathVariable Long productId) {
        return ResultEntity.success(flashSaleService.listFlashSaleProductsByProductId(productId));
    }

    @Log(value = "参数获取秒杀商品")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ResultEntity<EsFlashSaleProduct> getFlashSaleProductByParam(@RequestBody EsFlashSaleProduct queryParam) {
        return ResultEntity.success(flashSaleService.getFlashSaleProductByParam(queryParam));
    }

}

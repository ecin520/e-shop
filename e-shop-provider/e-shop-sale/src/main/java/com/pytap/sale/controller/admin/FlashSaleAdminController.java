package com.pytap.sale.controller.admin;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsFlashSaleProduct;
import com.pytap.sale.model.dto.FlashSaleDTO;
import com.pytap.sale.service.FlashSaleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/10/15 10:19
 */
@RestController
@RequestMapping("/admin/flash_sale")
public class FlashSaleAdminController {

    @Resource
    private FlashSaleService flashSaleService;

    @Log(value = "添加秒杀商品")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ResultEntity<Object> insertFlashSale(@RequestBody EsFlashSaleProduct flashSaleProduct) {
        return ResultEntity.success(flashSaleService.insertFlashSale(flashSaleProduct));
    }

    @Log(value = "移除秒杀商品")
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ResultEntity<Object> deleteFlashSale(@RequestBody FlashSaleDTO param) {
        return ResultEntity.success(flashSaleService.deleteFlashSaleByParam(param));
    }

    @Log(value = "更新秒杀商品")
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResultEntity<Object> updateFlashSale(@RequestBody EsFlashSaleProduct flashSaleProduct) {
        return ResultEntity.success(flashSaleService.updateFlashSale(flashSaleProduct));
    }

    @Log(value = "更新秒杀商品列表")
    @RequestMapping(value = "/list/update", method = RequestMethod.PUT)
    public ResultEntity<Object> updateFlashSales(@RequestBody List<EsFlashSaleProduct> flashSaleProducts) {
        return ResultEntity.success(flashSaleService.updateFlashSales(flashSaleProducts));
    }

    @Log(value = "通过商品id获取秒杀商品列表")
    @RequestMapping(value = "/list/{productId}", method = RequestMethod.GET)
    public ResultEntity<List<EsFlashSaleProduct>> listFlashSaleProductsByProductId(@PathVariable Long productId) {
        return ResultEntity.success(flashSaleService.listFlashSaleProductsByProductId(productId));
    }

}

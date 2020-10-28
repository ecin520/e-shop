package com.pytap.sale.controller.api;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsFlashSaleProduct;
import com.pytap.sale.model.dto.FlashSaleDTO;
import com.pytap.sale.service.FlashSaleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/10/13 11:00
 */
@RequestMapping("/open/feign/flash_sale")
@RestController
public class FlashSaleFeignClient {

    @Resource
    private FlashSaleService flashSaleService;

    @Log(value = "远程调用有效秒杀商品查询接口")
    @RequestMapping(value = "/param/valid/list", method = RequestMethod.POST)
    public ResultEntity<Pager<EsFlashSaleProduct>> listValidFlashSaleProductsByQueryParam(@RequestBody QueryParam<FlashSaleDTO> queryParam) {
        return ResultEntity.success(flashSaleService.listValidFlashSaleProductsByQueryParam(queryParam));
    }

    @Log(value = "远程调用秒杀商品查询接口")
    @PreAuthorize("hasRole('SYS_ADMIN')")
    @RequestMapping(value = "/param/list", method = RequestMethod.POST)
    public ResultEntity<Pager<EsFlashSaleProduct>> listFlashSaleProductsByQueryParam(@RequestBody QueryParam<FlashSaleDTO> queryParam) {
        return ResultEntity.success(flashSaleService.listFlashSaleProductsByQueryParam(queryParam));
    }

}

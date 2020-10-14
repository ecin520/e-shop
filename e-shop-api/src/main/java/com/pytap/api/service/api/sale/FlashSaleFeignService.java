package com.pytap.api.service.api.sale;

import com.pytap.api.model.dto.FlashSaleDTO;
import com.pytap.api.service.hystrix.sale.FlashSaleFeignHystrix;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsFlashSaleProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ecin520
 * @date 2020/10/13 14:21
 */
@FeignClient(value = "SALE-PROVIDER", fallbackFactory = FlashSaleFeignHystrix.class)
public interface FlashSaleFeignService {

    @RequestMapping(value = "/open/feign/flash_sale/param/list", method = RequestMethod.POST)
    ResultEntity<Pager<EsFlashSaleProduct>> listFlashSaleProductsByQueryParam (@RequestBody QueryParam<FlashSaleDTO> queryParam);

}

package com.pytap.api.service.api.sale;

import com.pytap.api.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ecin520
 * @date 2020/8/20 11:14
 */
@Component
@FeignClient(value = "SALE-PROVIDER", configuration = FeignClientConfig.class)
public interface SaleService {
    @RequestMapping(value = "/sale", method = RequestMethod.GET)
    String sale(String id);
}
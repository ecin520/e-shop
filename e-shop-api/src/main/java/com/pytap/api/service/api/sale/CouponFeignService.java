package com.pytap.api.service.api.sale;

import com.pytap.api.config.FeignClientConfig;
import com.pytap.api.service.hystrix.sale.CouponFeignHystrix;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsCoupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ecin520
 * @date 2020/8/20 11:14
 */
@FeignClient(value = "SALE-PROVIDER", fallbackFactory = CouponFeignHystrix.class)
public interface CouponFeignService {

    @RequestMapping(value = "/feign/coupon/query", method = RequestMethod.POST)
    ResultEntity<EsCoupon> getCoupon(@RequestBody EsCoupon queryParam);

}
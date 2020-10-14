package com.pytap.sale.controller.api;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsCoupon;
import com.pytap.sale.service.CouponService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/9/25 10:54
 */
@RequestMapping("/open/feign/coupon")
@RestController
public class CouponFeignClient {

    @Resource
    private CouponService couponService;

    @Log("远程调用优惠券查询接口")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ResultEntity<EsCoupon> getCoupon(@RequestBody EsCoupon queryParam) {
        return ResultEntity.success(couponService.getCoupon(queryParam));
    }

}

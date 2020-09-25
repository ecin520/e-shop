package com.pytap.sale.service;

import com.pytap.generator.entity.EsCoupon;

/**
 * @author Ecin520
 * @date 2020/9/25 10:56
 */
public interface CouponService {
    /**
     * 通过coupon拆性能参数查询优惠券
     * @param queryParam 优惠券参数
     * @return EsCoupon
     * */
    EsCoupon getCoupon(EsCoupon queryParam);
}

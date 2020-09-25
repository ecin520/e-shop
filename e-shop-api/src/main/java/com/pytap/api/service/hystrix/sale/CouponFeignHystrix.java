package com.pytap.api.service.hystrix.sale;

import com.pytap.api.service.api.sale.CouponFeignService;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsCoupon;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author Ecin520
 * @date 2020/9/25 11:13
 */
@Component
public class CouponFeignHystrix implements FallbackFactory<CouponFeignService> {

    @Override
    public CouponFeignService create(Throwable throwable) {
        return new CouponFeignService() {
            @Override
            public ResultEntity<EsCoupon> getCoupon(EsCoupon queryParam) {
                return ResultEntity.fail(500, "服务暂时不可用");
            }
        };
    }

}

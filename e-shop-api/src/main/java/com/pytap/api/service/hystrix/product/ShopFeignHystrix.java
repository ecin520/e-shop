package com.pytap.api.service.hystrix.product;

import com.pytap.api.service.api.product.ShopFeignService;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsShop;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author Ecin520
 * @date 2020/9/25 12:53
 */
@Component
public class ShopFeignHystrix implements FallbackFactory<ShopFeignService> {
    @Override
    public ShopFeignService create(Throwable throwable) {
        return new ShopFeignService() {
            @Override
            public ResultEntity<EsShop> getShop(EsShop queryParam) {
                return ResultEntity.fail(500, "服务暂时不可用");
            }
        };
    }
}

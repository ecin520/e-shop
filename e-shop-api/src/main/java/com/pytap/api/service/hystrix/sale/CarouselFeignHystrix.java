package com.pytap.api.service.hystrix.sale;

import com.pytap.api.service.api.sale.CarouselFeignService;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsProductCarousel;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author Ecin520
 * @date 2020/10/20 10:51
 */
@Component
public class CarouselFeignHystrix implements FallbackFactory<CarouselFeignService> {

    @Override
    public CarouselFeignService create(Throwable throwable) {
        return new CarouselFeignService() {
            @Override
            public ResultEntity<Pager<EsProductCarousel>> listProductCarousels(QueryParam<EsProductCarousel> queryParam) {
                return ResultEntity.fail(500, "服务暂时不可用");
            }
        };
    }
}

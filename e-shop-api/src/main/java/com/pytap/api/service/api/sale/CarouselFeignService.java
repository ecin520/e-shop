package com.pytap.api.service.api.sale;

import com.pytap.api.service.hystrix.sale.CarouselFeignHystrix;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsProductCarousel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ecin520
 * @date 2020/10/20 10:49
 */
@FeignClient(value = "SALE-PROVIDER", fallbackFactory = CarouselFeignHystrix.class)
public interface CarouselFeignService {
    @RequestMapping(value = "/open/feign/carousel/list", method = RequestMethod.POST)
    ResultEntity<Pager<EsProductCarousel>> listProductCarousels(@RequestBody QueryParam<EsProductCarousel> queryParam);
}

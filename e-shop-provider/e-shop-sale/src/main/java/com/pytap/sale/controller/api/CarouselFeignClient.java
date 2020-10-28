package com.pytap.sale.controller.api;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsProductCarousel;
import com.pytap.sale.service.CarouselService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/10/20 10:48
 */
@RequestMapping("/open/feign/carousel")
@RestController
public class CarouselFeignClient {

    @Resource
    private CarouselService carouselService;

    @Log(value = "获取轮播图列表")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ResultEntity<Pager<EsProductCarousel>> listProductCarousels(@RequestBody QueryParam<EsProductCarousel> queryParam) {
        return ResultEntity.success(carouselService.listProductCarousels(queryParam));
    }

}

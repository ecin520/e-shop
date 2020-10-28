package com.pytap.sale.controller.admin;

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
 * @date 2020/10/20 10:41
 */
@RestController
@RequestMapping("/admin/carousel")
public class CarouselAdminController {

    @Resource
    private CarouselService carouselService;

    @Log(value = "插入轮播图")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ResultEntity<Object> insertCarousel(@RequestBody EsProductCarousel productCarousel) {
        return carouselService.insertCarousel(productCarousel) != 1 ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "删除轮播图")
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ResultEntity<Object> deleteProductCarouselByParam(@RequestBody EsProductCarousel param) {
        return carouselService.deleteProductCarouselByParam(param) != 1 ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "更新轮播图")
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResultEntity<Object> updateProductCarousel(@RequestBody EsProductCarousel productCarousel) {
        return carouselService.updateProductCarousel(productCarousel) != 1 ? ResultEntity.fail() : ResultEntity.success();
    }

    @Log(value = "获取轮播图列表")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public  ResultEntity<Pager<EsProductCarousel>> listProductCarousels(@RequestBody QueryParam<EsProductCarousel> queryParam) {
        return ResultEntity.success(carouselService.listProductCarousels(queryParam));
    }
}

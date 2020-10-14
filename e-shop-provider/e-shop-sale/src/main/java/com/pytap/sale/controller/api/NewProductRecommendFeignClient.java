package com.pytap.sale.controller.api;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsNewProductRecommend;
import com.pytap.sale.service.NewProductRecommendService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/10/13 14:25
 */
@RequestMapping("/open/feign/new_product_recommend")
@RestController
public class NewProductRecommendFeignClient {

    @Resource
    private NewProductRecommendService newProductRecommendService;

    @Log("远程调用新品查询接口")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultEntity<Pager<EsNewProductRecommend>> listNewProductsRecommend(
            @RequestParam(defaultValue = "0") Integer pageNum,
            @RequestParam(defaultValue = "8") Integer pageSize) {
        return ResultEntity.success(newProductRecommendService.listNewProductsRecommend(pageNum, pageSize));
    }

}

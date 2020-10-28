package com.pytap.api.service.api.sale;

import com.pytap.api.service.hystrix.sale.NewProductRecommendFeignHystrix;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsNewProductRecommend;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Ecin520
 * @date 2020/10/13 14:28
 */
@FeignClient(value = "SALE-PROVIDER", fallbackFactory = NewProductRecommendFeignHystrix.class)
public interface NewProductRecommendFeignService {

    @RequestMapping(value = "/open/feign/new_product_recommend/list", method = RequestMethod.GET)
    ResultEntity<Pager<EsNewProductRecommend>> listNewProductsRecommend (@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize")  Integer pageSize);

}

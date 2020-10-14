package com.pytap.api.service.hystrix.sale;

import com.pytap.api.service.api.sale.NewProductRecommendFeignService;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsNewProductRecommend;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author Ecin520
 * @date 2020/10/13 14:32
 */
@Component
public class NewProductRecommendFeignHystrix implements FallbackFactory<NewProductRecommendFeignService> {

    @Override
    public NewProductRecommendFeignService create(Throwable throwable) {
        return new NewProductRecommendFeignService() {
            @Override
            public ResultEntity<Pager<EsNewProductRecommend>> listNewProductsRecommend(Integer pageNum, Integer pageSize) {
                return ResultEntity.fail(500, "服务暂时不可用");
            }
        };
    }
}

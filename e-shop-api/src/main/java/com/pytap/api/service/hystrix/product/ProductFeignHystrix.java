package com.pytap.api.service.hystrix.product;

import com.pytap.api.service.api.product.ProductFeignService;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsProduct;
import com.pytap.generator.entity.EsSkuProduct;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author Ecin520
 * @date 2020/9/25 11:16
 */
@Component
public class ProductFeignHystrix implements FallbackFactory<ProductFeignService> {

    @Override
    public ProductFeignService create(Throwable throwable) {
        return new ProductFeignService() {

            @Override
            public ResultEntity<EsProduct> getProduct(EsProduct queryParam) {
                return ResultEntity.fail(500, "服务暂时不可用");
            }

            @Override
            public ResultEntity<EsSkuProduct> getSkuProduct(EsSkuProduct queryParam) {
                return ResultEntity.fail(500, "获取商品信息失败，服务暂时不可用");
            }

            @Override
            public ResultEntity<Object> reduceSkuProductStock(Long id) {
                return ResultEntity.fail(500, "减库存失败，服务暂时不可用");
            }

            @Override
            public ResultEntity<Object> increaseSkuProductStock(Long id) {
                return ResultEntity.fail(500, "恢复库存失败，服务暂时不可用");
            }

        };
    }

}

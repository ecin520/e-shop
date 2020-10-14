package com.pytap.api.service.hystrix.sale;

import com.pytap.api.model.dto.FlashSaleDTO;
import com.pytap.api.service.api.sale.FlashSaleFeignService;
import com.pytap.common.utils.Pager;
import com.pytap.common.utils.QueryParam;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.EsFlashSaleProduct;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author Ecin520
 * @date 2020/10/13 14:31
 */
@Component
public class FlashSaleFeignHystrix implements FallbackFactory<FlashSaleFeignService> {

    @Override
    public FlashSaleFeignService create(Throwable throwable) {
        return new FlashSaleFeignService() {
            @Override
            public ResultEntity<Pager<EsFlashSaleProduct>> listFlashSaleProductsByQueryParam(QueryParam<FlashSaleDTO> queryParam) {
                return ResultEntity.fail(500, "服务暂时不可用");
            }
        };
    }

}

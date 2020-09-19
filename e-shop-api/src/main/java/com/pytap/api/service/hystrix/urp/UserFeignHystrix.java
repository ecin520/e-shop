package com.pytap.api.service.hystrix.urp;

import com.pytap.api.service.api.urp.UserFeignService;
import com.pytap.common.utils.ResultEntity;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author Ecin520
 * @date 2020/8/27 10:03
 */
@Component
public class UserFeignHystrix implements FallbackFactory<UserFeignService> {
    @Override
    public UserFeignService create(Throwable throwable) {
        return user -> ResultEntity.fail(500, "服务已降级，请等候服务器恢复。");
    }
}

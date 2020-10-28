package com.pytap.api.service.api.urp;

import com.pytap.api.service.hystrix.urp.UserFeignHystrix;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign远程调用
 * @author Ecin520
 * @date 2020/8/16 0:28
 */
@FeignClient(value = "URP-PROVIDER", fallbackFactory = UserFeignHystrix.class)
public interface UserFeignService {

    /**
     * 获取用户名
     * @return String
     * */
    @RequestMapping(value = "/test/token", method = RequestMethod.POST)
    ResultEntity<Object> updateUser(@RequestBody SysUser user);

    @RequestMapping(value = "/feign/user/judge", method = RequestMethod.GET)
    ResultEntity<Boolean> judgeUser(@RequestParam Long userId);

}

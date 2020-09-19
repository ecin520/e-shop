package com.pytap.urp.controller.api;

import com.pytap.common.annotation.Log;
import com.pytap.common.exception.GeneralException;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.SysUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 提供远程调用接口
 * @author Ecin520
 * @date 2020/8/26 16:16
 */
@RequestMapping("/feign/user")
@RestController
public class UserFeignClient {

    @Log("更新用户信息")
    @RequestMapping(value = "/test/token", method = RequestMethod.POST)
    public ResultEntity<Object> updateUser(@RequestBody SysUser user) throws GeneralException {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (user.getUsername().equals(currentUser)) {
            return ResultEntity.success("获取token成功");
        }
        throw new GeneralException(currentUser + " 禁止更改其他用户，违者封号");
    }
}

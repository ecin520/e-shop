package com.pytap.urp.controller.web;

import com.pytap.common.annotation.Limit;
import com.pytap.common.annotation.Log;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.SysUser;
import com.pytap.urp.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/8/27 14:09
 */
@RestController
public class CommonWebController {

    @Resource
    private UserService userService;

    @Log("用户注册")
    @Limit(seconds = 30, maxCount = 5)
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResultEntity<SysUser> register(@RequestBody SysUser user) {
        int result = userService.register(user);
        return 1 == result ? ResultEntity.success("注册成功") : ResultEntity.fail(400, "注册失败");
    }

}

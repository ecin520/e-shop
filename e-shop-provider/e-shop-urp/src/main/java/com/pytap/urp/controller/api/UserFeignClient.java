package com.pytap.urp.controller.api;

import com.pytap.common.annotation.Log;
import com.pytap.common.exception.GeneralException;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.SysUser;
import com.pytap.urp.model.vo.UserVO;
import com.pytap.urp.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 提供远程调用接口
 * @author Ecin520
 * @date 2020/8/26 16:16
 */
@RequestMapping("/feign/user")
@RestController
public class UserFeignClient {

    @Resource
    private UserService userService;

    @Log("远程通过用户名获取用户信息视图")
    @RequestMapping(value = "{username}", method = RequestMethod.GET)
    public ResultEntity<UserVO> getUserByUsername(@PathVariable String username) {
        return ResultEntity.success(userService.getUserVOByUsername(username));
    }

    @Log("更新用户信息")
    @RequestMapping(value = "/test/token", method = RequestMethod.POST)
    public ResultEntity<Object> updateUser(@RequestBody SysUser user) throws GeneralException {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (user.getUsername().equals(currentUser)) {
            return ResultEntity.success("获取token成功");
        }
        throw new GeneralException(currentUser + " 禁止更改其他用户，违者封号");
    }

    @Log("判断操作对象是否为当前用户")
    @RequestMapping(value = "/judge", method = RequestMethod.GET)
    public ResultEntity<Boolean> judgeUser(@RequestParam Long userId) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (null != userId) {
            SysUser user = userService.getUserById(userId);
            if (currentUser.equals(user.getUsername())) {
                return ResultEntity.success(true);
            }
        }
        return ResultEntity.success(false);
    }

}

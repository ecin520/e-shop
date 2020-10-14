package com.pytap.urp.controller.web;

import com.pytap.common.annotation.Log;
import com.pytap.common.utils.ResultEntity;
import com.pytap.log.utils.SecurityUtil;
import com.pytap.urp.model.vo.UserVO;
import com.pytap.urp.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/9/2 14:53
 */
@RequestMapping("/web/user")
@RestController
public class UserWebController {

    @Resource
    private UserService userService;

    @Log("用户名获取用户")
    @RequestMapping(value = "{username}", method = RequestMethod.GET)
    public ResultEntity<UserVO> getUserByUsername(@PathVariable String username) {
        UserVO user = userService.getUserVOByUsername(username);
        return null != user ? ResultEntity.success(user) : ResultEntity.fail(400, "用户不存在");
    }

    @Log("主键获取用户视图对象")
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ResultEntity<UserVO> getUserVOById(@PathVariable Long id) {
        UserVO userVO = userService.getUserVOById(id);
        return null != userVO ? ResultEntity.success(userVO) : ResultEntity.fail(400, "用户不存在");
    }

}

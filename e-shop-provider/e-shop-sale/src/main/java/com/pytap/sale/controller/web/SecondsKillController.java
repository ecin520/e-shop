package com.pytap.sale.controller.web;

import com.pytap.api.service.api.urp.UserFeignService;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.SysUser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Ecin520
 * @date 2020/8/26 15:39
 */
@RestController
public class SecondsKillController {

    @Resource
    private UserFeignService userFeignService;

    @RequestMapping(value = "/fuck", method = RequestMethod.POST)
    public ResultEntity<Object> updateUser(@RequestBody SysUser sysUser) {
        return userFeignService.updateUser(sysUser);
    }

}

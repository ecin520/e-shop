package com.pytap.api.service.api.oauth2;

import com.pytap.api.config.FeignClientConfig;
import com.pytap.common.utils.ResultEntity;
import com.pytap.generator.entity.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Ecin520
 * @date 2020/8/20 17:47
 */
@Component
@FeignClient(value = "OAUTH2-SERVER", configuration = FeignClientConfig.class)
public interface AuthService {

    @RequestMapping(value = "/core/user/pk", method = RequestMethod.GET)
    ResultEntity<SysUser> getUserById(@RequestParam("id") Long id);

    @RequestMapping(value = "/core/user/um", method = RequestMethod.GET)
    ResultEntity<SysUser> getUserByUsername(String username);

}

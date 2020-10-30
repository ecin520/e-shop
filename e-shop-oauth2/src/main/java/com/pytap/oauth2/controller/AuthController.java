package com.pytap.oauth2.controller;

import com.alibaba.fastjson.JSONObject;
import com.pytap.common.annotation.Limit;
import com.pytap.common.constant.AuthConstant;
import com.pytap.common.constant.RedisKey;
import com.pytap.common.exception.GeneralException;
import com.pytap.common.utils.JsonUtil;
import com.pytap.common.utils.ResultEntity;
import com.pytap.common.utils.SecretUtil;
import com.pytap.generator.entity.SysRole;
import com.pytap.generator.entity.SysUser;
import com.pytap.oauth2.model.vo.Oauth2TokenVO;
import com.pytap.oauth2.model.vo.UserVO;
import com.pytap.oauth2.service.AuthService;
import com.pytap.oauth2.utils.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Ecin520
 * @date 2020/8/18 11:46
 */
@RequestMapping(value = "/oauth")
@RestController
public class AuthController {

    @Resource
    private TokenEndpoint tokenEndpoint;

    @Resource
    private ConsumerTokenServices consumerTokenServices;

    @Resource
    private AuthService authService;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 自定义登录返回结果
     */
    @Limit(seconds = 30, maxCount = 10)
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResultEntity<JSONObject> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws GeneralException, HttpRequestMethodNotSupportedException {

        // 如果是刷新token，则直接拦截刷新token
        if (!StringUtils.isEmpty(parameters.get("refresh_token"))) {
            OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
            Oauth2TokenVO oauth2TokenVO = new Oauth2TokenVO();
            if (oAuth2AccessToken != null) {
                oauth2TokenVO.setAccessToken(oAuth2AccessToken.getValue());
                oauth2TokenVO.setRefreshToken(oAuth2AccessToken.getRefreshToken().getValue());
                oauth2TokenVO.setExpiresIn(oAuth2AccessToken.getExpiresIn());
                oauth2TokenVO.setTokenHead("Bearer ");
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("oauth", oauth2TokenVO);
            return ResultEntity.success(jsonObject);
        }

        boolean flag = false;
        SysUser user = null;

        // 解密密码
        String password = SecretUtil.decrypt(parameters.get("password"));
        parameters.put("password", password);

        // 判断登陆类型为手机号登录还是用户名登录
        if (!StringUtils.isEmpty(parameters.get("login_type")) && "telephone".equals(parameters.get("login_type"))) {
            if (!StringUtils.isEmpty(parameters.get("telephone"))) {
                user = authService.getUserByTelephone(parameters.get("telephone"));
            }
        } else if (!StringUtils.isEmpty(parameters.get("login_type")) && "username".equals(parameters.get("login_type"))) {
            if (!StringUtils.isEmpty(parameters.get("username"))) {
                user = authService.getUserByUsername(parameters.get("username"));
            }
        } else {
            throw new GeneralException(JsonUtil.ExceptionObject(403, "登陆异常！"));
        }

        if (null == user) {
            throw new GeneralException(JsonUtil.ExceptionObject(403, "账户不存在！"));
        }

        // 如果client_type为ADMIN，则为管理员账户登录类型
        if ("ADMIN".equals(parameters.get("client_type"))) {
            // 检查是否为管理员
            List<SysRole> list = authService.listUserRoles(user.getId());
            for (SysRole sysRole : list) {
                if (("ROLE_" + AuthConstant.ROLE_SYS_ADMIN).equals(sysRole.getName())) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                throw new GeneralException(JsonUtil.ExceptionObject(403, "非管理员禁止登录！"));
            }
        }

        OAuth2AccessToken oAuth2AccessToken;

        if (null == parameters.get("username") || "".equals(parameters.get("username"))) {
            parameters.put("username", user.getUsername());
        }

        oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();

        Oauth2TokenVO oauth2TokenVO = new Oauth2TokenVO();
        if (oAuth2AccessToken != null) {
            oauth2TokenVO.setAccessToken(oAuth2AccessToken.getValue());
            oauth2TokenVO.setRefreshToken(oAuth2AccessToken.getRefreshToken().getValue());
            oauth2TokenVO.setExpiresIn(oAuth2AccessToken.getExpiresIn());
            oauth2TokenVO.setTokenHead("Bearer ");
        }

        UserVO vo = new UserVO();

        // 复制到传输对象并更新登陆时间
        BeanUtils.copyProperties(user, vo);
        user.setLoginTime(new Date());
        authService.updateUserInfo(user);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("oauth", oauth2TokenVO);
        jsonObject.put("userInfo", vo);

        // 将用户名对应的用户id放到redis中，以便后续操作
        redisUtil.set(RedisKey.USER_KEY + user.getUsername(), user.getId());

        return ResultEntity.success(jsonObject);
    }

    /**
     * 退出登录，实质是删除token
     */
    @Limit(seconds = 30, maxCount = 5)
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResultEntity<Object> logout(String token) {
        return consumerTokenServices.revokeToken(token) ? ResultEntity.success("退出成功") : ResultEntity.fail(400, "退出失败");
    }

}

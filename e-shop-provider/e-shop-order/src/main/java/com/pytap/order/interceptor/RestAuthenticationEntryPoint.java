package com.pytap.order.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.pytap.common.constant.HttpCode;
import com.pytap.common.utils.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token失效
 * @author Ecin520
 * @date 2020/8/22 1:17
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        logger.error(e.getMessage());
        response.getWriter().println(JSONObject.toJSONString(ResultEntity.fail(HttpCode.FORBIDDEN, e.getMessage())));
        response.getWriter().flush();
    }
}

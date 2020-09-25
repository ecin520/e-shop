package com.pytap.product.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.pytap.common.constant.HttpCode;
import com.pytap.common.utils.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 没有权限
 * @author Ecin520
 * @date 2020/9/1 14:36
 */
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        logger.error(e.getMessage());
        response.getWriter().println(JSONObject.toJSONString(ResultEntity.fail(HttpCode.OAUTH2_NO_AUTH, e.getMessage())));
        response.getWriter().flush();
    }
}

package com.pytap.sale.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ecin520
 * @date 2020/8/26 18:20
 */
@Component
public class TokenInterceptor implements RequestInterceptor {

    protected final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {

        HttpServletRequest request = null;

        if(RequestContextHolder.getRequestAttributes() != null) {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }

        String token = request != null ? request.getHeader("Authorization") : null;

        logger.info("客户端传递token: {}", token);

        requestTemplate.header("Authorization", token);

    }

}
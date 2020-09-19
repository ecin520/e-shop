package com.pytap.product.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * feign调用远程服务的时候携带token
 * @author Ecin520
 * @date 2020/8/16 14:03
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

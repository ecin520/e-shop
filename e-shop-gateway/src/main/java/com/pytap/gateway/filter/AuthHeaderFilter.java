package com.pytap.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Zuul本身不会向服务中传递token，因此添加一个过滤器来向服务中添加token
 * @author Ecin520
 * @date 2020/8/26 16:52
 */
@Component
public class AuthHeaderFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthHeaderFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        return !request.getMethod().equals("OPTIONS");
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request != null ? request.getHeader("Authorization") : null;
        logger.info("token: {}", token);
        requestContext.addZuulRequestHeader("Authorization", token);
        return null;
    }

}

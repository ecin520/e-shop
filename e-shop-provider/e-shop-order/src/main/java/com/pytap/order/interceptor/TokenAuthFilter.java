package com.pytap.order.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 资源服务器过滤器，首先在授权服务器进行权限判断，然后在此过滤，在这里过滤的同时，通过username再次判断权限
 * @author Ecin520
 * @date 2020/8/21 10:15
 */
@Component
public class TokenAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@Nullable HttpServletRequest request,
                                    @Nullable HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(request, response);
    }

}

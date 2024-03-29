package com.pytap.urp.config;

import com.pytap.common.constant.AuthConstant;
import com.pytap.urp.interceptor.RestAccessDeniedHandler;
import com.pytap.urp.interceptor.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源配置
 * @author Ecin520
 * @date 2020/8/17 0:21
 */
@Configuration
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        // 允许iframe嵌入网页
        httpSecurity.headers().frameOptions().disable();
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers( "/actuator/**", "/register").permitAll()
                .antMatchers( "/actuator/**").permitAll()
                .antMatchers("/druid/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/admin/**").hasRole(AuthConstant.ROLE_SYS_ADMIN)
                .anyRequest().authenticated();
    }

    /**
     * 自定义返回异常
     * */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .accessDeniedHandler(new RestAccessDeniedHandler());
    }
}

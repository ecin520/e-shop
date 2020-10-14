package com.pytap.oauth2.config;

import com.pytap.common.constant.AuthConstant;
import com.pytap.generator.entity.SysUser;
import com.pytap.oauth2.model.bo.UserDetailsImpl;
import com.pytap.oauth2.model.dto.AuthDTO;
import com.pytap.oauth2.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

/**
 * 安全配置
 * @author Ecin520
 * @date 2020/8/16 17:13
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Resource
    private AuthService authService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    /**
     * 注册认证管理器
     * */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers( "/actuator/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/oauth/**", "/auth/**", "/admin/**").permitAll()
                .antMatchers("/login").permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 内部匿名类，通过用户名查询判断
     * */
    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            SysUser user = authService.getUserByUsername(username);
            logger.info("用户：{} 开始认证", username);
            if (user != null) {
                logger.info("用户：{} 开始授权", username);
                List<AuthDTO> list = authService.listUserAllRolePermissions(user.getId());
                return new UserDetailsImpl(user, list);
            } else {
                throw new UsernameNotFoundException("User does not exist");
            }
        };
    }
}

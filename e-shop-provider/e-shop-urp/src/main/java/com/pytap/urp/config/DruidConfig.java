package com.pytap.urp.config;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * druid配置中心
 * @author Ecin520
 * @date 2020/9/22 10:30
 */
@Configuration
public class DruidConfig {

    /**
     * 配置监控面板的账户密码及访问
     * */
    @Bean
    public ServletRegistrationBean<StatViewServlet> servletRegistrationBean() {
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(new StatViewServlet(),  "/druid/*");
        registrationBean.addInitParameter("allow", "127.0.0.1");
        registrationBean.addInitParameter("deny", "");
        registrationBean.addInitParameter("loginUsername", "root");
        registrationBean.addInitParameter("loginPassword", "guowei00a");
        registrationBean.addInitParameter("resetEnable", "false");
        return registrationBean;
    }

}

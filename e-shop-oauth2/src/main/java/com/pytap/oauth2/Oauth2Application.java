package com.pytap.oauth2;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 认证授权中心，支持单点登录，分布式登陆系统
 * 这里只提供用户，角色权限等等都在各个服务中实现
 * @author Ecin520
 * @date 2020/8/16 14:49
 */
@EnableEurekaClient
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
public class Oauth2Application {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Oauth2Application.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}

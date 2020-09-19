package com.pytap.eureka;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务注册与发现1
 * @author Ecin520
 * @date 2020/8/11 20:54
 */
@EnableEurekaServer
@SpringBootApplication
public class Eureka1ThApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Eureka1ThApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}

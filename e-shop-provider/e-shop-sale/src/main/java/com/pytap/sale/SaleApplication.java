package com.pytap.sale;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Ecin520
 * @date 2020/8/17 16:45
 */
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients("com.pytap.api")
@SpringBootApplication(scanBasePackages = {"com.pytap.sale", "com.pytap.log"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SaleApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SaleApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}

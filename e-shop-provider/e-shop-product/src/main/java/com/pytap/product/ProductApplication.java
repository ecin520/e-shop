package com.pytap.product;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Ecin520
 * @date 2020/9/9 15:46
 */
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients("com.pytap.api")
@SpringBootApplication(scanBasePackages = {"com.pytap.product", "com.pytap.log"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ProductApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
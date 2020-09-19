package com.pytap.sale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Ecin520
 * @date 2020/8/17 16:45
 */
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients("com.pytap.api")
@SpringBootApplication(scanBasePackages = {"com.pytap.api", "com.pytap.sale"})
public class SaleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaleApplication.class, args);
    }
}

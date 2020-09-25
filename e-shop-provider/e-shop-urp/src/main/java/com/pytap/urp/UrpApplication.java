package com.pytap.urp;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * user role permission 模块, 向注册中心提供urp相关服务
 * @author Ecin520
 * @date 2020/8/11 23:21
 */
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients("com.pytap.api")
@SpringBootApplication(scanBasePackages = {"com.pytap.urp", "com.pytap.log", "com.pytap.api"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
public class UrpApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(UrpApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

}

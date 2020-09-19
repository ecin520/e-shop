package com.pytap.urp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 接口文档配置
 * @author Ecin520
 * @date 2020/9/11 10:39
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pytap.urp"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .description("易商城接口文档")
                .termsOfServiceUrl("http://www.pytap.com")
                .contact(new Contact("Ecin520", "http://www.pytap.com", "15607942340@163.com"))
                .version("1.0")
                .build();
    }

}

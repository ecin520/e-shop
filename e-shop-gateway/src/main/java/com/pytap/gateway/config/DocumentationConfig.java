package com.pytap.gateway.config;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/11 10:44
 */
@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        // 手动添加
        resources.add(swaggerResource("OAUTH2-SERVER", "/api/auth/v2/api-docs"));
        resources.add(swaggerResource("URP-PROVIDER", "/api/urp/v2/api-docs"));
        resources.add(swaggerResource("PRODUCT-PROVIDER", "/api/product/v2/api-docs"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}

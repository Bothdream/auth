package com.zte.auth.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * open api 接口分组
 * 根据匹配的Url分组
 */
@Configuration
public class OpenAPIConfig {

    /**
     *  文档说明
     * @return OpenAPI
     */
     @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                         .info(new Info().title("Spring Auth API")
                        .description("Spring application")
                        .version("3.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

    /**
     * 用户接口的API
     */
    @Bean
    public GroupedOpenApi userApi(){
        return GroupedOpenApi.builder()
                .group("user-api")
                .pathsToMatch("/**/users/**")
                .build();
    }
    /**
     * 角色接口的API
     */
    @Bean
    public GroupedOpenApi roleApi(){
        return GroupedOpenApi.builder()
                .group("role-api")
                .pathsToMatch("/**/roles/**")
                .build();
    }
    /**
     * 权限接口的API
     */
    @Bean
    public GroupedOpenApi permissionApi(){
        return GroupedOpenApi.builder()
                .group("permissions-api")
                .pathsToMatch("/**/permissions/**")
                .build();
    }


    /**
     * 测试接口的API
     */
    @Bean
    public GroupedOpenApi testApi(){
        return GroupedOpenApi.builder()
                .group("test-api")
                .pathsToMatch("/**/tests/**")
                .build();
    }
}


package com.xinrui.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Configuration {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.xinrui"))
                //路径过滤器（扫描所有路径）
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("鑫瑞建材后台管理系统api文档")
                //描述信息
                .description("鑫瑞建材后台管理系统api文档")
                //服务网站
//                .termsOfServiceUrl("/")
                //版本号
                .version("1.0")
                //创建人信息
                .contact("vonco")
                .build();
    }
//    http://localhost:8080/swagger-ui.html
}

package com.xinrui.config;

import com.xinrui.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 过滤器配置
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    /**
     * 初始化拦截器
     * @return
     */
    @Bean
    public JwtInterceptor getJwtInterceptor(){
        return  new JwtInterceptor();
    }

    /**
     * 自定义路径映射
     * @param registry
     */
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
           registry.addViewController("/").setViewName("login");
    }

    /**
     * 添加拦截器的配置
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //1.添加自定义拦截器
        registry.addInterceptor(getJwtInterceptor()).
                addPathPatterns("/**").//2.指定拦截器的url地址
                excludePathPatterns("/v2/api-docs", "/swagger-resources/configuration/ui", "/swagger-resources","/swagger-resources/configuration/security", "/swagger-ui.html","/webjars/**",
                "/login/**","/page/**","/index","/",
                "/dataJs/**","/js/**","/json/**","/layui/**","/lib/**","/modules/**","/style/**","/tpl/**","/config.js","/favicon.ico");//3.指定不拦截的url地址
    }

    /**
     * 配置静态资源映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //静态资源
        registry.addResourceHandler("/**")
                .addResourceLocations( "static/")
                .addResourceLocations("classpath:static/");
        //swagger相关
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}

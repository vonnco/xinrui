package com.xinrui;

import com.xinrui.framework.common.interceptor.FeignClientInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

@EnableCircuitBreaker
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EntityScan("com.xinrui.framework.model")
@ComponentScan(basePackages={"com.xinrui.api"})
@ComponentScan(basePackages={"com.xinrui.framework.common"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    //feign请求拦截器
    @Bean
    public FeignClientInterceptor feignClientInterceptor(){
        return new FeignClientInterceptor();
    }

    //解决no session
    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
        return new OpenEntityManagerInViewFilter();
    }
}

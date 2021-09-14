package com.xinrui.aop;

import com.xinrui.dataSource.ChooseDataSource;
import com.xinrui.dataSource.DataSourceHandler;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(-9999)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceAspect {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 配置前置通知,使用在方法aspect()上注册的切入点
     */
    @Before("execution(* com.xinrui.service.*.*(..))")
    @Order(-9999)
    public void before(JoinPoint point) {
        String className = point.getTarget().getClass().getName();
        String method = point.getSignature().getName();
        logger.info(className + "." + method + "(" +
                Arrays.asList(point.getArgs())+ ")");
        try {
            for (String key : ChooseDataSource.METHOD_TYPE_MAP.keySet()) {
                for (String type : ChooseDataSource.METHOD_TYPE_MAP.get(key)) {
                    if (method.startsWith(type)) {
                        System.out.println("key : " + key);
                        DataSourceHandler.putDataSource(key);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

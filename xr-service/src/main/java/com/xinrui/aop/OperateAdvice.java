package com.xinrui.aop;

import com.alibaba.fastjson.JSON;
import com.xinrui.framework.model.OperationLog;
import com.xinrui.framework.model.ext.UserExt;
import com.xinrui.service.OperationLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Component
@Aspect
public class OperateAdvice {
    private static Logger log = LoggerFactory.getLogger(OperateAdvice.class);
    @Autowired
    private OperationLogService operationLogService;
    @Around("execution(* com.xinrui.controller.*.*(..)) && @annotation(operateLog)")
    public Object insertLogAround(ProceedingJoinPoint pjp , OperateLog operateLog) throws Throwable{
        System.out.println(" ************************ 记录日志 [start] ****************************** ");
        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取session
        HttpSession session = request.getSession();
        UserExt userExt = (UserExt)session.getAttribute("userExt");
        OperationLog op = new OperationLog();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        op.setOperateTime(sdf.format(new Date()));
        op.setOperateUser(userExt.getUsername());
        op.setOperateClass(pjp.getTarget().getClass().getName());
        op.setOperateMethod(pjp.getSignature().getName());
        //获取方法调用时传递的参数
        Object[] args = pjp.getArgs();
        op.setParamAndValue(Arrays.toString(args));
        long start_time = System.currentTimeMillis();
        //放行
        Object object = pjp.proceed();
        long end_time = System.currentTimeMillis();
        op.setCostTime(end_time - start_time);
        if(object != null){
            op.setReturnClass(object.getClass().getName());
            op.setReturnValue(object.toString());
        }else{
            op.setReturnClass("java.lang.Object");
            op.setParamAndValue("void");
        }
        log.error(JSON.toJSONString(op));
        operationLogService.insert(op);
        System.out.println(" ************************** 记录日志 [end] *************************** ");
        return object;
    }
}

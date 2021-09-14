package com.xinrui.interceptor;

import com.xinrui.framework.common.exception.ExceptionCast;
import com.xinrui.framework.common.model.response.CommonCode;
import com.xinrui.framework.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
    @Value("${jwt.key}")
    private String key;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //打印拦截的路径
        String requestURI=request.getRequestURI();
        System.out.println("preHandle拦截的请求路径是"+requestURI);
        // 1.通过request获取请求token信息
        String authorization = request.getHeader("Authorization");
        //判断请求头信息是否为空，或者是否已Bearer开头
        if (!StringUtils.isEmpty(authorization) && authorization.startsWith("Bearer")) {
            //获取token数据
            String token = authorization.replace("Bearer ", "");
            JwtUtil jwtUtil = new JwtUtil();
            //设置私钥
            jwtUtil.setKey(key);
            //解析token获取claims
            Claims claims = null;
            try {
                claims = jwtUtil.parseJwt(token);
            } catch (Exception e) {
                e.getMessage();
            }
            if (claims != null) {
                //通过claims获取到当前用户的可访问API权限字符串
                String menuCodes = (String) claims.get("menuCodes");
                //通过handler
                HandlerMethod h = (HandlerMethod) handler;
                //获取接口上的reqeustmapping注解
                RequestMapping annotation = h.getMethodAnnotation(RequestMapping.class);
                //获取当前请求接口中的name属性
                String name = annotation.name();
                //判断当前用户是否具有响应的请求权限
                if (menuCodes.contains(name)) {
                    request.setAttribute("user_claims", claims);
                    return true;
                } else {
                    ExceptionCast.cast(CommonCode.UNAUTHORISE);
                    return false;
                }
            } else {
                //RedirecUtil.redirect(request,response,"/login/userlogout");
                ExceptionCast.cast(CommonCode.INVALID_LOGIN);
                return false;
            }
        }
        ExceptionCast.cast(CommonCode.UNAUTHENTICATED);
        return false;
    }
}

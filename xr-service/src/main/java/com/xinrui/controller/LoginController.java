package com.xinrui.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xinrui.client.AuthClient;
import com.xinrui.framework.common.model.response.Response;
import com.xinrui.framework.model.ext.UserExt;
import com.xinrui.framework.model.request.LoginRequest;
import com.xinrui.framework.model.response.JwtResult;
import com.xinrui.framework.model.response.LoginResult;
import com.xinrui.framework.utils.Oauth2Util;
import com.xinrui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController{
    @Autowired
    private AuthClient authClient;
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String login(){
        return "/login";
    }

    @HystrixCommand(fallbackMethod = "fallbackLogin")
    @PostMapping("/userlogin")
    public String login(LoginRequest loginRequest, Model model) {
        LoginResult loginResult = authClient.login(loginRequest);
        if (loginResult.isSuccess()) {
            model.addAttribute("token",loginResult.getToken());
            //获取jwt令牌
            JwtResult jwtResult = authClient.getUserJwtByToken(loginResult.getToken());
            String jwtToken = jwtResult.getJwt();
            model.addAttribute("jwtToken",jwtToken);
            //解析jwt令牌内容
            Map<String, String> jwtClaimsFromToken = Oauth2Util.getJwtClaimsFromToken(jwtToken);
            //根据用户名查询用户信息
            UserExt userExt = userService.findUserByUsername(jwtClaimsFromToken.get("user_name"));
            model.addAttribute("userExt",userExt);
            return "/index";
        } else {
            model.addAttribute("msg",loginResult.getMsg());
            return "/login";
        }
    }

    //登录报错回调方法
    public String fallbackLogin(LoginRequest loginRequest, Model model){
        model.addAttribute("msg","测试Hystrix熔断");
        return "/login";
    }

    @GetMapping("/userlogout")
    public String logout() {
        authClient.logout();
        return "redirect:/login";
    }

    @GetMapping("/userjwt")
    @ResponseBody
    public JwtResult userJwt() {
        return authClient.userJwt();
    }

    @GetMapping("/getUserjwtByToken")
    @ResponseBody
    public JwtResult getUserJwtByToken(String token) {
        return authClient.getUserJwtByToken(token);
    }
}

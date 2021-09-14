package com.xinrui.controller;

import com.alibaba.druid.util.StringUtils;
import com.xinrui.api.LoginControllerApi;
import com.xinrui.framework.common.model.response.CommonCode;
import com.xinrui.framework.model.Menu;
import com.xinrui.framework.model.ext.UserExt;
import com.xinrui.framework.model.request.LoginRequest;
import com.xinrui.framework.model.response.AuthCode;
import com.xinrui.framework.model.response.LoginResult;
import com.xinrui.framework.utils.JwtUtil;
import com.xinrui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/login")
public class LoginController implements LoginControllerApi {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String login(){
        return "/login";
    }

    @Value("${jwt.key}")
    private String key;

    @Value("${jwt.ttl}")
    private Long ttl;

    @Override
    //@HystrixCommand(fallbackMethod = "fallbackLogin")
    @PostMapping("/userlogin")
    @ResponseBody
    public LoginResult login(LoginRequest loginRequest) {
        //校验账号是否输入
        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getUsername())){
            return new LoginResult(AuthCode.AUTH_USERNAME_NONE,null);
        }
        //校验密码是否输入
        if(StringUtils.isEmpty(loginRequest.getPassword())){
            return new LoginResult(AuthCode.AUTH_PASSWORD_NONE,null);
        }
        //根据用户名查询用户信息
        UserExt userExt = userService.findUserByUsername(loginRequest.getUsername());
        //创建BCryptPasswordEncoder对象用于校验密码
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //判断是否登录成功
        if (userExt != null && passwordEncoder.matches(loginRequest.getPassword(),userExt.getPassword())) {
            //创建jwt工具类
            JwtUtil jwtUtil = new JwtUtil();
            //设置私钥
            jwtUtil.setKey(key);
            //设置失效时间
            jwtUtil.setTtl(ttl);
            //创建jwt令牌
            Map<String,Object> map = new HashMap<>();
            String menuCodes = userExt.getMenuList().stream().map(Menu::getMenuCode).collect(Collectors.joining(","));
            map.put("menuCodes",menuCodes);
            String token = jwtUtil.createJwt(userExt.getId(), userExt.getUsername(), map);
            return new LoginResult(CommonCode.SUCCESS,token);
        } else {
            return new LoginResult(AuthCode.AUTH_CREDENTIAL_ERROR,null);
        }
    }

    //登录报错回调方法
    /*public String fallbackLogin(LoginRequest loginRequest, Model model){
        model.addAttribute("msg","测试Hystrix熔断");
        return "/login";
    }*/

    @Override
    @GetMapping("/userlogout")
    public String logout() {
        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取session
        HttpSession session = request.getSession();
        session.removeAttribute("menuCodeList");
        return "redirect:/login";
    }
}

package com.xinrui.client;

import com.xinrui.framework.common.client.XrServiceList;
import com.xinrui.framework.common.model.response.ResponseResult;
import com.xinrui.framework.model.request.LoginRequest;
import com.xinrui.framework.model.response.JwtResult;
import com.xinrui.framework.model.response.LoginResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = XrServiceList.XR_SERVICE_AUTH)
public interface AuthClient {
    @PostMapping("/auth/userlogin")
    public LoginResult login(@RequestBody LoginRequest loginRequest);

    @GetMapping("/auth/userlogout")
    public ResponseResult logout();

    @GetMapping("/auth/userjwt")
    public JwtResult userJwt();

    @GetMapping("/auth/getUserjwtByToken")
    public JwtResult getUserJwtByToken(@RequestParam String token);
}

package com.xinrui.api;

import com.xinrui.framework.model.request.LoginRequest;
import com.xinrui.framework.model.response.LoginResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户登录接口",description = "用户登录接口，提供用户的登录、退出")
public interface LoginControllerApi {
    @ApiOperation("用户登录")
    public LoginResult login(LoginRequest loginRequest);

    @ApiOperation("用户退出")
    public String logout();
}

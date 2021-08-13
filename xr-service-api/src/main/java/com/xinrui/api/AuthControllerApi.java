package com.xinrui.api;

import com.xinrui.framework.common.model.response.ResponseResult;
import com.xinrui.framework.model.request.LoginRequest;
import com.xinrui.framework.model.response.JwtResult;
import com.xinrui.framework.model.response.LoginResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户认证",description = "用户认证接口")
public interface AuthControllerApi {
    @ApiOperation("登录")
    public LoginResult login(LoginRequest loginRequest);
    @ApiOperation("退出")
    public ResponseResult logout();
    @ApiOperation("查询userjwt令牌")
    public JwtResult userJwt();
    @ApiOperation("根据token查询userjwt令牌")
    public JwtResult getUserJwtByToken(String token);
}

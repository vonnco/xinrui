package com.xinrui.framework.model.request;

import com.xinrui.framework.common.model.request.RequestData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "登录请求对象",description = "登录请求对象LoginRequest")
public class LoginRequest extends RequestData {
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("令牌")
    private String verifyCode;
}

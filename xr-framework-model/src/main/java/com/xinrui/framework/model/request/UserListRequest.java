package com.xinrui.framework.model.request;

import com.xinrui.framework.common.model.request.RequestData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "用户列表请求对象",description = "用户列表请求对象LoginRequest")
public class UserListRequest extends RequestData {
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("性别")
    private Integer sex;
    @ApiModelProperty("手机号")
    private String tel;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("状态")
    private Integer state;
}

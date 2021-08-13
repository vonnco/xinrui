package com.xinrui.framework.model.response;

import com.google.common.collect.ImmutableMap;
import com.xinrui.framework.common.model.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@ToString
public enum UserCode implements ResultCode {
    USER_ADD_FAIL(false,31001,"用户添加失败！"),
    USER_CHECKPASSWORD_FAIL(false,31002,"校验用户密码失败！"),
    USER_DELETE_FAIL(false,31003,"用户删除失败！"),
    USER_ISNULL(false,31004,"用户不存在！");
    //操作代码
    boolean success;
    //操作代码
    int code;
    //提示信息
    String msg;
    private UserCode(boolean success, int code, String msg){
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String msg() {
        return msg;
    }
}

package com.xinrui.framework.common.exception;

import com.xinrui.framework.common.model.response.ResultCode;

public class ExceptionCast {
    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
}

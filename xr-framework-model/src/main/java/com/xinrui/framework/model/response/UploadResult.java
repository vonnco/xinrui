package com.xinrui.framework.model.response;

import com.xinrui.framework.common.model.response.ResponseResult;
import com.xinrui.framework.common.model.response.ResultCode;

public class UploadResult<T> extends ResponseResult {
    private T data;
    public UploadResult (ResultCode resultCode,T data){
        super(resultCode);
        this.data = data;
    }
}

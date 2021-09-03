package com.xinrui.framework.model.request;

import com.xinrui.framework.common.model.request.RequestData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "操作日志列表请求对象",description = "操作日志列表请求对象OperationLogListRequest")
public class OperationLogListRequest extends RequestData {
    @ApiModelProperty("操作用户")
    private String operateUser;
    @ApiModelProperty("操作方法")
    private String operateMethod;
    @ApiModelProperty("返回值类型")
    private String returnClass;
    @ApiModelProperty("执行方法耗时")
    private Long costTime;
}

package com.xinrui.api;

import com.xinrui.framework.common.model.response.QueryResponseResult;
import com.xinrui.framework.model.request.OperationLogListRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "操作日志管理接口",description = "操作日志管理接口，提供操作日志的查询")
public interface OperationLogControllerApi {
    @ApiOperation("查找操作日志")
    public QueryResponseResult findOperationLogList(OperationLogListRequest operationLogListRequest);
}

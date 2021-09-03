package com.xinrui.controller;

import com.xinrui.api.OperationLogControllerApi;
import com.xinrui.framework.common.model.response.QueryResponseResult;
import com.xinrui.framework.model.request.OperationLogListRequest;
import com.xinrui.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operationLog")
public class OperationLogController implements OperationLogControllerApi {
    @Autowired
    private OperationLogService operationLogService;

    @Override
    @GetMapping("/findOperationLogList")
    public QueryResponseResult findOperationLogList(OperationLogListRequest operationLogListRequest) {
        QueryResponseResult queryResponseResult = operationLogService.findOperationLogList(operationLogListRequest);
        if (queryResponseResult.isSuccess()) {
            queryResponseResult.setCode(0);
        }
        return queryResponseResult;
    }
}

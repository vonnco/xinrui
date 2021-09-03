package com.xinrui.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xinrui.dao.OperationLogMapper;
import com.xinrui.dao.OperationLogRepository;
import com.xinrui.framework.common.model.response.CommonCode;
import com.xinrui.framework.common.model.response.QueryResponseResult;
import com.xinrui.framework.model.OperationLog;
import com.xinrui.framework.model.request.OperationLogListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationLogService {
    @Autowired
    private OperationLogRepository operationLogRepository;
    @Autowired
    private OperationLogMapper operationLogMapper;
    public OperationLog insert(OperationLog op) {
        OperationLog save = operationLogRepository.save(op);
        return save;
    }

    public QueryResponseResult findOperationLogList(OperationLogListRequest operationLogListRequest) {
        PageHelper.startPage(operationLogListRequest.getPage(),operationLogListRequest.getLimit());
        Page<OperationLog> page = operationLogMapper.findOperationLogList(operationLogListRequest);
        return new QueryResponseResult(CommonCode.SUCCESS,page.getResult(),page.getTotal());
    }
}

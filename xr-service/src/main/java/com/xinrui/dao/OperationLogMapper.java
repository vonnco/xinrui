package com.xinrui.dao;

import com.github.pagehelper.Page;
import com.xinrui.framework.model.OperationLog;
import com.xinrui.framework.model.request.OperationLogListRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OperationLogMapper {
    Page<OperationLog> findOperationLogList(OperationLogListRequest operationLogListRequest);
}

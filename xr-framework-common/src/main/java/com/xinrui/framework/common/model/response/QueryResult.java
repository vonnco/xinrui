package com.xinrui.framework.common.model.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class QueryResult<T> {
    //数据列表
    private List<T> data;
    //数据总数
    private long count;
}

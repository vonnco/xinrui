package com.xinrui.framework.common.model.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class QueryResponseResult<T> extends ResponseResult {
    //数据总数
    private long count;
    //数据
    private List<T> data;

    public QueryResponseResult(ResultCode resultCode,List<T> data,long count){
        super(resultCode);
        this.data = data;
        this.count = count;
    }
}

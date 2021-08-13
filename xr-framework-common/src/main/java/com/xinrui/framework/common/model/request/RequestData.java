package com.xinrui.framework.common.model.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RequestData {
    private int page;//页码
    private int limit;//每页条数
}

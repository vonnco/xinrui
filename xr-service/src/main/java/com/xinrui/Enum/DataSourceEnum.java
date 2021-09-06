package com.xinrui.Enum;

import com.xinrui.framework.common.Enum.EnumInterface;

/**
 * 数据库枚举
 */
public enum DataSourceEnum implements EnumInterface {
    MASTER("master"),
    SLAVE("slave");
    private String value;
    DataSourceEnum(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
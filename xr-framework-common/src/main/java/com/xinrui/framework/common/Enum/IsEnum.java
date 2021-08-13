package com.xinrui.framework.common.Enum;

/**
 *是否枚举
 */
public enum IsEnum implements EnumInterface{
    是(0), 否(1);
    private Integer value;

    IsEnum(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}

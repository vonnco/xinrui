package com.xinrui.framework.common.Enum;

/**
 *状态枚举
 */
public enum StateEnum implements EnumInterface{
    正常(0), 删除(99);
    private Integer value;

    StateEnum(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}

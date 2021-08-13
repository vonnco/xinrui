package com.xinrui.Enum;

import com.xinrui.framework.common.Enum.EnumInterface;

/**
 *性别枚举
 */
public enum UserSexEnum implements EnumInterface {
    男(0), 女(1);
    private Integer value;

    UserSexEnum(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}

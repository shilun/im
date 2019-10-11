package com.im.domain.model;

import com.common.util.IGlossary;

/**
 * 聊天内类型
 */
public enum DataTypeEnum implements IGlossary {
    Sign("单聊",1),
    Group("群聊",2)
    ;

    private String name;
    private Integer value;
    DataTypeEnum(String name,Integer value) {
        this.name=name;
        this.value=value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }
}

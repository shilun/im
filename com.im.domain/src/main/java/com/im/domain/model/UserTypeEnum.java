package com.im.domain.model;

import com.common.util.IGlossary;


public enum UserTypeEnum implements IGlossary {
    owner("群主"),
    admin("管理员"),
    user("成员");
    private String name;

    UserTypeEnum(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}

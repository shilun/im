package com.im.domain.model;

import com.common.util.IGlossary;

/**
 * 表情在型
 */
public enum  EmojiTypeEnum implements IGlossary {

    EmojiPackage("表情包"),
    Custom("自定义")
    ;

    private String name;
    EmojiTypeEnum(String name) {
        this.name=name;

    }


    public String getName() {
        return name;
    }
}

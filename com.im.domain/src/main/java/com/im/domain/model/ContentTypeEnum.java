package com.im.domain.model;

import com.common.util.IGlossary;

/**
 * 内容类型
 */
public enum ContentTypeEnum implements IGlossary {
    text("文本"),
    file("文件"),
    svg("svg"),
    html("网页"),
    img("图片"),
    audi("音频"),
    video("视频"),
    m3u8("m3u8"),
    emoji("表情"),
    app("app"),
    h5app("h5app")
    ;

    private String name;
    ContentTypeEnum(String name) {
        this.name=name;

    }

    public String getName() {
        return name;
    }
}

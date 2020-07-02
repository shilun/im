package com.im.domain;

import com.common.util.AbstractBaseEntity;
import com.im.domain.model.EmojiTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * 表情信息
 */
@Data
public class EmojiInfo extends AbstractBaseEntity {
    /**
     * 名字
     */
    private String name;
    /**
     * 表情索引
     */
    private Integer index;
    /**
     * 表情项
     */
    private List<String> items;
    /**
     * 表情类型
     */
    private EmojiTypeEnum type;
    /**
     * 上架状态
     */
    private Boolean upStatus;
}

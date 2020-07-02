package com.im.domain;

import com.common.util.AbstractBaseEntity;
import lombok.Data;

/**
 * 表情内容
 */
@Data
public class EmojiInfoItem extends AbstractBaseEntity {
    /**
     * 表情索引
     */
    private Integer index;
    /**
     * 内容
     */
    private String content;

}

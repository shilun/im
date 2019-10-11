package com.im.domain;

import com.common.util.AbstractBaseEntity;
import lombok.Data;

@Data
public  class ChatContent extends AbstractBaseEntity {
    /**
     * 发送者
     */
    private String senderPin;

    /**
     * 1 单聊
     * 2 群聊
     */
    private Integer dataType;
    /**
     * 内容
     */
    private String content;

}

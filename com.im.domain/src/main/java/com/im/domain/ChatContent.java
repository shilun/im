package com.im.domain;

import com.common.util.AbstractBaseEntity;
import lombok.Data;

@Data
/**
 * 消息只存放于redis
 * 当单聊时消息接收确认时直接清楚
 * 对于群聊保留1周数据
 */
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

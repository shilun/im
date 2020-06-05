package com.im.domain;

import com.im.domain.model.ContentTypeEnum;
import lombok.Data;

@Data
/**
 * 消息只存放于redis
 * 当单聊时消息接收确认时直接清楚
 * 对于群聊保留1周数据
 */
public  class Message {
    /**
     * 发送者
     */
    private String senderPin;

    /**
     * 内容类型
     */
    private ContentTypeEnum contentType;
    /**
     * 内容
     */
    private String content;

}

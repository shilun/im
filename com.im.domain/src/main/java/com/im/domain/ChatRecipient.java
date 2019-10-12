package com.im.domain;

import lombok.Data;

@Data
/**
 * 聊天接收者
 */
/**
 * 消息只存放于redis
 */
public class ChatRecipient {
    /**
     * 内容标识
     */
    private String contentId;
    /**
     * 接收者
     */
    private String recipientPin;
}

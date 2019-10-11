package com.im.domain;

import lombok.Data;

@Data
public class ChatRecipient {
    /**
     * 内容标识
     */
    private String cotentId;
    /**
     * 接收者
     */
    private String recipientPin;
}

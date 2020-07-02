package com.im.domain;

import com.common.util.AbstractBaseEntity;
import com.im.domain.model.ContentTypeEnum;
import lombok.Data;

@Data
public class UserOfflineMsg extends AbstractBaseEntity {
    /**
     * 内容
     */
    private String content;

    /**
     * 内容类型
     */
    private ContentTypeEnum contentType;

    /**
     * 接收者
     */
    private String recipientPin;

}

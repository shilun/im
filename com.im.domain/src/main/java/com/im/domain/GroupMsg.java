package com.im.domain;

import com.common.util.AbstractBaseEntity;
import com.im.domain.model.ContentTypeEnum;
import lombok.Data;

/**
 * 组信息
 */
@Data
public class GroupMsg extends AbstractBaseEntity {
    /**
     * 组id
     */
    private String groupId;
    /**
     * 内容
     */
    private String content;
    /**
     * 内容类型
     */
    private ContentTypeEnum contentType;
    /**
     * 发送者
     */
    private String senderPin;
}

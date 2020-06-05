package com.im.domain;

import com.im.domain.model.UserTypeEnum;
import lombok.Data;

@Data
/**
 * 组成员
 */
public class GroupUserRel {
    /**
     * 组管标识
     */
    private String groupId;
    /**
     * 用户类型
     */
    private UserTypeEnum userType;
    /**
     * 组成员标识
     */
    private String userPin;
}

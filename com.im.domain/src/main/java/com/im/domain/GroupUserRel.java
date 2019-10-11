package com.im.domain;

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
     * 组成员标识
     */
    private String userPin;
}

package com.im.domain;

import com.common.util.AbstractBaseEntity;
import lombok.Data;

@Data
/**
 * 组管理员关系
 */
public class GroupAdminRel extends AbstractBaseEntity {
    /**
     * 组标识
     */
    private String groupId;
    /**
     * 管理员账户
     */
    private String adminPin;


}

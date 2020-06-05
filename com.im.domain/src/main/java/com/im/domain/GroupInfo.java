package com.im.domain;

import com.common.util.AbstractBaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class GroupInfo extends AbstractBaseEntity {
    /**
     *
     */
    private String title;
    /**
     * 群签名
     */
    private String sign;

    /**
     * 群图标
     */
    private String icon;

    /**
     * 组用户
     */
    private List<GroupUserRel> users;

    /**
     * 描述
     */
    private String desc;
}

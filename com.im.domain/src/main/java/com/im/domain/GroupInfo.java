package com.im.domain;

import com.common.util.AbstractBaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

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
}

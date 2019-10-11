package com.im.domain.route;

import com.common.util.AbstractBaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * 服务器信息
 */
@Data
@Document
@CompoundIndexes(
        {
                @CompoundIndex(name = "uniqueIPIndex", def = "{'ip':1}", unique = true),
        })
public class ServerInfo extends AbstractBaseEntity {
    /**
     * ip地址
     */
    private String ip;
    /**
     * 最大连接数
     */
    private Integer maxClient;
}

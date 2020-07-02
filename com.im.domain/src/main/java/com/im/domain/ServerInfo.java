package com.im.domain;

import com.common.util.AbstractBaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;


/**
 * 服务器信息
 */
@Data
@Document
@CompoundIndexes(
        {
                @CompoundIndex(name = "uniqueIPIndex", def = "{'ip':1,port:1}", unique = true),
        })
public class ServerInfo extends AbstractBaseEntity {
    /**
     * ip地址
     */
    private String ip;

    /**
     * 端口
     */
    private Integer port;
    /**
     * 最大连接数
     */
    @Max(1000)
    private Integer maxClient;
}

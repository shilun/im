package com.im.service;

import com.common.mongo.MongoService;
import com.im.domain.ServerInfo;

public interface ServerInfoService extends MongoService<ServerInfo> {
    /**
     * 查找可用的服务器
     *
     * @return
     */
    ServerInfo findAvailable();
}

package com.im.socket.netty;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * MyChannelHandlerPool
 * 通道组池，管理所有websocket连接
 *
 * @author
 * @date 2019-06-12
 */
@Component
public class WebSocketlHandlerPool {

    @Resource
    public ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

}


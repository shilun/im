package com.im.socket.application;

import com.im.socket.netty.BinaryWebSocketFrameHandler;
import com.im.socket.netty.TextWebSocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketFrameAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component
public class NettyServer  {
    private EventLoopGroup boss = null;
    private EventLoopGroup worker = null;
    @Autowired
    private TextWebSocketHandler serverHandler;
    @PostConstruct
    public void init()  {
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void start() throws Exception {
        //负责处理客户端连接的线程池
        boss = new NioEventLoopGroup();
        //负责处理读写操作的线程池
        worker = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new LoggingHandler(LogLevel.TRACE))
                                // HttpRequestDecoder和HttpResponseEncoder的一个组合，针对http协议进行编解码
                                .addLast(new HttpServerCodec())
                                // 分块向客户端写数据，防止发送大文件时导致内存溢出， channel.write(new ChunkedFile(new File("bigFile.mkv")))
                                .addLast(new ChunkedWriteHandler())
                                // 将HttpMessage和HttpContents聚合到一个完成的 FullHttpRequest或FullHttpResponse中,具体是FullHttpRequest对象还是FullHttpResponse对象取决于是请求还是响应
                                // 需要放到HttpServerCodec这个处理器后面
                                .addLast(new HttpObjectAggregator(10240))
                                // webSocket 数据压缩扩展，当添加这个的时候WebSocketServerProtocolHandler的第三个参数需要设置成true
                                .addLast(new WebSocketServerCompressionHandler())
                                // 聚合 websocket 的数据帧，因为客户端可能分段向服务器端发送数据
                                // https://github.com/netty/netty/issues/1112 https://github.com/netty/netty/pull/1207
                                .addLast(new WebSocketFrameAggregator(10 * 1024 * 1024))
                                // 服务器端向外暴露的 web socket 端点，当客户端传递比较大的对象时，maxFrameSize参数的值需要调大
                                .addLast(new WebSocketServerProtocolHandler("/chat", null, true, 10485760))
                                // 自定义处理器 - 处理 web socket 文本消息
                                .addLast(serverHandler)
                                // 自定义处理器 - 处理 web socket 二进制消息
                                .addLast(new BinaryWebSocketFrameHandler());

                    }
                });
        bind(serverBootstrap, 8989);
    }

    /**
     * 如果端口绑定失败，端口数+1,重新绑定
     *
     * @param serverBootstrap
     * @param port
     */
    public void bind(final ServerBootstrap serverBootstrap,int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                log.info("端口[ {} ] 绑定成功",port);
            } else {
                log.error("端口[ {} ] 绑定失败", port);
                bind(serverBootstrap, port + 1);
            }
        });
    }

    @PreDestroy
    public void destory() throws InterruptedException {
        boss.shutdownGracefully().sync();
        worker.shutdownGracefully().sync();
        log.info("关闭Netty");
    }
}

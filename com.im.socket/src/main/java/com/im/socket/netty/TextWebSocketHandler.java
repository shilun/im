package com.im.socket.netty;

import com.common.exception.ApplicationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.im.socket.annotation.Controller;
import com.im.socket.annotation.MessageMapping;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@Order
@ChannelHandler.Sharable
public class TextWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> implements ApplicationContextAware {
    protected static Map<String, MessageMethod> socketController = new HashMap<>();

    private ApplicationContext applicationContext;

    @Autowired
    private ObjectMapper mapper;

    @Resource
    private WebSocketlHandlerPool handlerPool;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Controller.class);
        beansWithAnnotation.values().forEach(item -> {
            Method[] methods = item.getClass().getMethods();
            for (Method method : methods) {
                MessageMapping annotation = method.getAnnotation(MessageMapping.class);
                if (annotation == null) {
                    continue;
                }
                String destination = annotation.value();
                Object o = socketController.get(destination);
                if (o != null) {
                    log.error("websocket订阅重复,destination{} 重复", destination);
                    throw new ApplicationException("websocket订阅重复");
                }
                MessageMethod messageMethod = new MessageMethod();
                messageMethod.setBean(item);
                messageMethod.setDestination(destination);
                messageMethod.setMethod(method);
                socketController.put(destination, messageMethod);
            }
        });
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        handlerPool.channelGroup.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        handlerPool.channelGroup.remove(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        String contenxt = msg.text();
        try {
            MessageData tt = new MessageData();
            tt.setDestination("/login/in");
            tt.setContent("fdsadfsa");
            contenxt = mapper.writeValueAsString(tt);
            MessageData messageData = mapper.readValue(contenxt, MessageData.class);
            MessageMethod method = socketController.get(messageData.getDestination());
            if (method == null) {
                log.warn("websocket.not.define.{}", messageData.getDestination());
                return;
            }
            Parameter[] parameters = method.getMethod().getParameters();
            if (parameters.length > 2) {
                log.error(method.getBean().getClass().getSimpleName() + "." + method.getMethod().getName() + ".define.error,不能大于2个参数");
                return;
            }
            Object[] values = new Object[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                Parameter item = parameters[0];
                Object value = null;
                if (item.getType() == ChannelHandlerContext.class) {
                    value = ctx;
                } else {
                    mapper.readValue(messageData.getContent(), item.getType());
                }
                values[i] = value;
            }
            method.getMethod().invoke(method.bean, values);
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        log.error("服务器发生了异常:", cause);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            log.info("web socket 握手成功。");
            WebSocketServerProtocolHandler.HandshakeComplete handshakeComplete = (WebSocketServerProtocolHandler.HandshakeComplete) evt;
            String requestUri = handshakeComplete.requestUri();
            log.info("requestUri:[{}]", requestUri);
            String subproTocol = handshakeComplete.selectedSubprotocol();
            log.info("subproTocol:[{}]", subproTocol);
            handshakeComplete.requestHeaders().forEach(entry -> log.info("header key:[{}] value:[{}]", entry.getKey(), entry.getValue()));
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}

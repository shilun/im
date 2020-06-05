package com.im.socket.netty;

import lombok.Data;

import java.lang.reflect.Method;

@Data
class MessageMethod {
    Object bean;
    String destination;
    Method method;
}

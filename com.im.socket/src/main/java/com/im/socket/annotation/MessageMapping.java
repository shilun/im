package com.im.socket.annotation;

import java.lang.annotation.*;

/**
 * 消息
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface MessageMapping {
    /**
     * 路径
     * @return
     */
    String value() ;
}

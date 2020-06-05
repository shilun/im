package com.im.socket.netty;

import lombok.Data;

@Data
class MessageData {
    String destination;
    String content;
}

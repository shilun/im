package com.im.socket.application;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.im.socket"})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}

	@Bean
	public ChannelGroup channelGroup(){
		return new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	}
}


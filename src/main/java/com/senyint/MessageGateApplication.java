package com.senyint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MessageGateApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageGateApplication.class, args);
	}

}

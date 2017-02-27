package com.senyint.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class HandlerFactory {

	@Autowired
	Environment env;

	@Autowired
	ApplicationContext app;

	Handler handler;

	public Handler getHandler(String HandlerMethodName) {

		String handlerName = env.getProperty(HandlerMethodName + ".handler");

		try {
			handler = (Handler) app.getBean(handlerName);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("找不到handler:" + handlerName);
		}

		return handler;
	}
}

package com.senyint.serv.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.senyint.handler.Handler;
import com.senyint.handler.HandlerFactory;

public class BaseServ {

	@Autowired
	HandlerFactory handlerFactory;

	public void executeHandlerList(String methodName) {
		List<Handler> handlerList = handlerFactory.getHandler(methodName);
		handlerList.forEach(iteam -> iteam.execute());
	}
}

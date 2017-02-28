package com.senyint.serv.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.senyint.handler.Handler;
import com.senyint.handler.HandlerFactory;

public class BaseServ {

	@Autowired
	HandlerFactory handlerFactory;

	public void executeHandlerList(Map<String,Object> map) {
		List<Handler> handlerList = handlerFactory.getHandler((String) map.get("commondName"));
		handlerList.forEach(iteam -> iteam.execute(map));
	}
}

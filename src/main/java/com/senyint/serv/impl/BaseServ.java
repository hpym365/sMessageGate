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
		List<Map<String, Object>> handlerList = handlerFactory.getHandler((String) map.get("commondName"));
		handlerList.forEach(iteamMap -> {
			Handler handler = (Handler) iteamMap.get("handler");
//			String config = (String) iteamMap.get("config");
//			map.put("config", config);
//			map.put("config", config);
			map.putAll(iteamMap);
			handler.execute(map);
		});
	}
}

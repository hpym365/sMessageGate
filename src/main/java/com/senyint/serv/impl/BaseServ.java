package com.senyint.serv.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.handler.HandlerFactory;

public class BaseServ {

	@Autowired
	HandlerFactory handlerFactory;

	public void executeHandlerList(DataStore dataStore) {
		List<Config> handlerList = handlerFactory.getHandler(dataStore.getRequestCommand());
		handlerList.forEach(cfg -> {
			dataStore.put(cfg.getHandler().toString(), cfg);

			cfg.getHandler().execute(dataStore);
			// Handler handler = (Handler) iteamMap.get("handler");
			// String config = (String) iteamMap.get("config");
			// map.put("config", config);
			// map.put("config", config);

			// handler.execute(map);
		});
	}
}

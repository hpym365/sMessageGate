package com.senyint.serv.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.handler.HandlerFactory;

public class BaseServ {

	@Autowired
	Environment env;

	@Autowired
	HandlerFactory handlerFactory;

	public void executeHandlerList(DataStore dataStore) {

		this.getConfig(dataStore);

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

	public void getConfig(DataStore dataStore) {
		String encoding = env.getProperty(dataStore.getRequestCommand() + ".encoding") == null ? "UTF-8"
				: env.getProperty(dataStore.getRequestCommand() + ".encoding");// 默认 utf-8
		dataStore.setEncoding(encoding);
	}
}

package com.senyint.serv.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.handler.HandlerFactory;
import com.senyint.util.PropertiesUtils;

public class BaseServ {

	@Autowired
	HandlerFactory handlerFactory;

	public void executeHandlerList(DataStore dataStore) {

		this.getDataStoreConfig(dataStore);

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

	// 每个service共享一个datastore 所以公用的配置放这里 config是每个handler的
	public void getDataStoreConfig(DataStore dataStore) {
		// 每个handler都需要读取的配置
		String dataTag = PropertiesUtils.getProperties(dataStore.getRequestCommand() + ".dataTag");
		dataStore.setDataTag(dataTag);

		String encoding = PropertiesUtils.getProperties(dataStore.getRequestCommand() + ".encoding", "UTF-8");
		dataStore.setEncoding(encoding);
	}

}

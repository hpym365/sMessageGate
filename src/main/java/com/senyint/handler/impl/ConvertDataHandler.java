package com.senyint.handler.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senyint.config.ScriptEngine;
import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.handler.BaseHandler;
import com.senyint.handler.Handler;

@Component("CONVERTDATA")
public class ConvertDataHandler extends BaseHandler implements Handler {

	@Autowired
	ScriptEngine engine;
	// @Autowired
	// HandlerFactory handlerFactory;
	//
	// public void executeDep(List depList){
	// List<Config> configList = handlerFactory.getHandlerByList(depList);
	// for(int i=0;i<configList.size();i++){
	// Config cfg = (Config) configList.get(i);
	// Map map = new HashMap();
	// map.put("config", cfg);
	// cfg.getHandler().execute(map);
	// }
	// }

	@SuppressWarnings("unused")
	public void execute(DataStore dataStore) {
		Config config = this.getConfig(dataStore);// 当前handler的配置

		Object[] params = { dataStore.getOrginData(), dataStore.getSelectList(), dataStore.getResultData() ,dataStore.getTempData()};
		Object result = engine.runScriptConvertData(config.getScriptType(), config.getScriptFile(),
				config.getFunName(), params);
		Map<String,Object> resMap = (Map<String, Object>) result;
		dataStore.setResultData(resMap.get("resultData"));
		dataStore.setTempData(resMap.get("tempData"));
		System.out.println("ConvertDataHandler execute");
		// System.out.println(config);
	}
}

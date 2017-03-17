package com.senyint.handler.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senyint.component.ScriptEngine;
import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.handler.BaseHandler;
import com.senyint.handler.Handler;
import com.senyint.handler.HandlerFactory;
import com.senyint.util.PropertiesUtils;

/*
 */
@Component("BRANCH")
public class BranchHandler extends BaseHandler implements Handler {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	ScriptEngine engine;
	
	@Autowired
	HandlerFactory handlerFactory;

	@Override
	// @Autowired
	public void execute(DataStore dataStore) {
		Config config = this.getConfig(dataStore);// 当前handler的配置

		Map<String, String> tempStringData = dataStore.getTempStringData();
		String judegeFlag = tempStringData.get("judgeFlag");

		String handlerListStr = PropertiesUtils.getProperties(config.getHandlerConfig() + "." + judegeFlag);

		List<Map<String, Object>> list = handlerFactory.convertJsonToList(handlerListStr);
		
		List<Config> handlerList = handlerFactory.getHandlerListByList(list);
		
		handlerFactory.executeHandlerList(handlerList, dataStore);
		System.out.println(dataStore.getTempData());
	}

}

package com.senyint.handler.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senyint.config.ScriptEngine;
import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.handler.BaseHandler;
import com.senyint.handler.Handler;

/*
 */
@Component("DATABASE")
public class DatabaseHandler extends BaseHandler implements Handler {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	ScriptEngine engine;

	@Override
	// @Autowired
	public void execute(DataStore dataStore) {
		Config config = this.getConfig(dataStore);// 当前handler的配置

		Object[] params = { dataStore.getOrginData(), dataStore.getTempData(), dataStore.getResultData() };

		engine.runScriptExecSql(dataStore, config.getScriptType(), config.getScriptFile(), config.getFunName(),
				config.getJdbcTemplate(), params);
		// Object res = GroovyUtils.runGroovyScriptByFile(null, script, "hello",
		// new Map[] { map });
		// dataStore.getOrginData().get("DATA");

		System.out.println(dataStore.getTempData());
	}

}

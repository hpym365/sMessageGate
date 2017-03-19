package com.senyint.handler.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senyint.component.ScriptEngine;
import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.handler.BaseHandler;
import com.senyint.handler.Handler;

/**
 * 
 * @ClassName: JudgeHandler
 * @Description: 判断的Handler，配置对应的执行Js或Groovy 返回判断结果，
 * 配合BranchHandler来根据不同情况执行分支HandlerList
 * @author hpym365@gmail.com
 * @date 2017年3月19日 下午9:38:07
 * @version V1.0
 */
@Component("IF")
public class JudgeHandler extends BaseHandler implements Handler {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	ScriptEngine engine;

	@Override
	// @Autowired
	public void execute(DataStore dataStore) {
		Config config = this.getConfig(dataStore);// 当前handler的配置

		Object[] params = { dataStore.getOrginData(), dataStore.getTempData(), dataStore.getResultData() };

		Object res = engine.runScriptByConfig(config.getScriptType(), config.getScriptFile(), config.getFunName(),
				params);

		// Object res = GroovyUtils.runGroovyScriptByFile(null, script, "hello",
		// new Map[] { map });
		// dataStore.getOrginData().get("DATA");
		Map<String, String> tempStringData = dataStore.getTempStringData();
		tempStringData.put("judgeFlag", (String) res);
		dataStore.setTempStringData(tempStringData);

		System.out.println(dataStore.getTempData());
	}

}

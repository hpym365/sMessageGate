package com.senyint.handler.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senyint.component.ScriptEngine;
import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.handler.BaseHandler;
import com.senyint.handler.Handler;

/**
 * @ClassName: ConvertDataHandler
 * @Description: 数据转换Handler，根据其他handler计算结果， 通过配置好的Js或Groovy文件进行转换，
 *               参数为OrginData tempData resultData
 *               将配置dataStore内的tempData等计算出的数据合并放到resultData内
 * @author hpym365@gmail.com
 * @date 2017年3月19日 下午9:43:41
 * @version V1.0
 */
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

	@SuppressWarnings("unchecked")
	public void execute(DataStore dataStore) {
		Config config = this.getConfig(dataStore);// 当前handler的配置

		Object[] params = { dataStore.getOrginData(), dataStore.getTempData(), dataStore.getResultData() };
		Object result = engine.runScriptConvertData(config.getScriptType(), config.getScriptFile(), config.getFunName(),
				params);
		Map<String, Object> resMap = (Map<String, Object>) result;
		if (resMap.get("resultData") != null) {
//			Map<String, Object> resultData = dataStore.getResultData();
//			resultData.putAll((Map<String, Object>) resMap.get("resultData"));
			dataStore.setResultData(resMap.get("resultData"));
		}
		if (resMap.get("tempData") != null) {
			Map<String, Object> tempData = dataStore.getTempData();
			tempData.putAll((Map<String, Object>) resMap.get("tempData"));
			dataStore.setTempData(tempData);
		}
		// dataStore.setTempData(resMap.get("tempData"));
		System.out.println("ConvertDataHandler execute");
		// System.out.println(config);
	}
}

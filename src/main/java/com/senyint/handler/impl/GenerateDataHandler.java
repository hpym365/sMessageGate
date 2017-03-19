package com.senyint.handler.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.senyint.component.TemplateConvert;
import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.handler.BaseHandler;
import com.senyint.handler.Handler;
import com.senyint.log.SenyintLog;

/**
 * @ClassName: GenerateDataHandler
 * @Description: 根据模板生成数据Handler，配置对应的模板，该handler会将dataStore内的resultData对应放入
 * @author hpym365@gmail.com
 * @date 2017-3-19 21:51:28
 * @version V1.0
 */
@Component("GENERATEDATA")
public class GenerateDataHandler extends BaseHandler implements Handler {

	public void execute(DataStore dataStore) {
		Config config = this.getConfig(dataStore);// 当前handler的配置
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("result", dataStore.getResultData());
		String result = "";
		try {
			result = TemplateConvert.convertMapByTemplate(config.getTemplateFileName(), res);
		} catch (Exception e) {
			SenyintLog.error(e);
		}

		dataStore.setOutResult(result);
		System.out.println("GenerateDataHandler execute");
		// System.out.println(config);
	}
}

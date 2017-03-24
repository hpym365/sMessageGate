package com.senyint.handler.impl;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.handler.BaseHandler;
import com.senyint.handler.Handler;
import com.senyint.log.SenyintLog;
import com.senyint.util.CallWebServiceUtils;

/**
 * @ClassName: WebServiceHandler
 * @Description: webservice调用 以后会支持 cxf axis2 等多类型框架
 * @author hpym365@gmail.com
 * @date 2017-3-19 21:51:28
 * @version V1.0
 */
@Component("WEBSERVICE")
public class WebServiceHandler extends BaseHandler implements Handler {

	public void execute(DataStore dataStore) {
		Config config = this.getConfig(dataStore);// 当前handler的配置
		Map<String, Object> tempData = dataStore.getTempData();
		String webServData = (String) tempData.get(config.getGetTempDataKey());
		String webserviceRes = null;
		try {
			webserviceRes = (String) CallWebServiceUtils.callWebServiceAxis(config.getUrl(), config.getNameSpace(),
					config.getMethodName(), config.getParamName(), new Object[] { webServData });
		} catch (Exception e) {
			// TODO Auto-generated catch block
			SenyintLog.error(e);
		}

		tempData.put(config.getSaveTempDataKey(), webserviceRes);
		dataStore.setTempData(tempData);
		dataStore.setOutResult(webserviceRes);
		dataStore.setInput(webserviceRes);
		System.out.println("WebServiceHandler execute");
		// System.out.println(config);
	}
}

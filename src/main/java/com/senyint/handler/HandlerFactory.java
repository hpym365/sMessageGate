package com.senyint.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class HandlerFactory {

	@Autowired
	Environment env;

	@Autowired
	ApplicationContext app;

	public List<Map<String, Object>> getHandler(String methodName) {

		String handlerStr = env.getProperty(methodName + ".serv.handlerlist");
		if (handlerStr == null)
			throw new RuntimeException("请检查配置文件:未配置" + methodName + ".serv.handlerlist");

		String[] handlerArr = null;
		try {
			handlerArr = handlerStr.split(",");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					methodName + ".serv.handlerlist:配置转换数组错误,配置样例qrsqd.serv.list=getdata,conVerTData。Tip:不区分大小写");
		}
		List<Map<String, Object>> handlerInstanceList = new ArrayList<Map<String, Object>>();
		Handler handler = null;

		for (int i = 0; i < handlerArr.length; i++) {
			String handlerIteam = handlerArr[i];
			Map<String, Object> handlerMap = new HashMap<String, Object>();

			try {
				// handlerIteam.split(":");
				handler = (Handler) app.getBean(handlerIteam.split(":")[0].toUpperCase());
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException("找不到handler:" + handlerIteam.split(":")[0].toUpperCase());
			}
			handlerMap.put("handler", handler);
			handlerMap.put("index", i);
			handlerMap.put("config", handlerIteam.split(":").length > 1 ? handlerIteam.split(":")[1] : null);
			handlerInstanceList.add(handlerMap);
		}

		// Arrays.asList(handlerStr).forEach((iteam) -> {
		// try {
		// Handler handler = (Handler) app.getBean(iteam.toUpperCase());
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// throw new RuntimeException("找不到handler:" + iteam);
		// }
		// handlerInstanceList.add(handler);
		// });
		// handlerArr
		return handlerInstanceList;
	}
}

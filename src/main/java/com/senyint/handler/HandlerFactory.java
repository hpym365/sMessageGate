package com.senyint.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	public List<Handler> getHandler(String methodName) {

		String handlerStr = env.getProperty(methodName + ".serv.handlerlist");
		if(handlerStr==null)
			throw new RuntimeException("请检查配置文件:未配置" + methodName + ".serv.handlerlist");
		
		String[] handlerArr = null;
		try{
			handlerArr = handlerStr.split(",");
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(methodName + ".serv.handlerlist:配置转换数组错误,配置样例qrsqd.serv.list=getdata,conVerTData。Tip:不区分大小写");
		}
		List<Handler> handlerInstanceList = new ArrayList<Handler>();
		Handler handler = null;

		for (String handlerIteam :handlerArr){
			try {
				handler = (Handler) app.getBean(handlerIteam.toUpperCase());
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException("找不到handler:" + handlerIteam);
			}
			handlerInstanceList.add(handler);
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

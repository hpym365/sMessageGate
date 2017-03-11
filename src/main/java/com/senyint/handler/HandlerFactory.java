package com.senyint.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.senyint.entity.Config;
import com.senyint.util.JsonUtil;
import com.senyint.util.PropertiesUtils;

@Component
public class HandlerFactory {


	@Autowired
	ApplicationContext app;

	@SuppressWarnings("unchecked")
	public List<Config> getHandler(String methodName) {

		String handlerStr = PropertiesUtils.getProperties(methodName + ".serv.handlerlist");
		if (handlerStr == null)
			throw new RuntimeException("请检查配置文件:未配置" + methodName + ".serv.handlerlist");

//		String[] handlerArr = null;
//		try {
//			handlerArr = handlerStr.split(",");
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(
//					methodName + ".serv.handlerlist:配置转换数组错误,配置样例qrsqd.serv.list=getdata,conVerTData。Tip:不区分大小写");
//		}

		Map<String,Object> map = null;
		try {
			map = JsonUtil.json2Map(handlerStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(map);

		List<Map<String,Object>> handlerlist = (List<Map<String, Object>>) map.get("hanlderlist");

		// for (int i = 0; i < handlerArr.length; i++) {
		// Map configMap;
		// try {
		// configMap = JsonUtil.json2Map(handlerStr);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// Config cfg = new Config();
		// String[] configArr = handlerIteam.split(":");
		// try {
		// // handlerIteam.split(":");
		// handler = (Handler) app.getBean(configArr[0].toUpperCase());
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// throw new RuntimeException("找不到handler:" +
		// configArr[0].toUpperCase());
		// }
		// cfg.setHandler(handler);
		// cfg.setIndex(i);
		// cfg.setHandlerConfig(configArr.length > 1 ? configArr[1] : null);
		//
		// if(configArr.length > 2){//有依赖配置配置
		// String depStr = configArr[2];
		// cfg.setDepList(null);
		// }
		//
		// }
		//
		// for (int i = 0; i < handlerArr.length; i++) {
		// String handlerIteam = handlerArr[i];
		// Map<String, Object> handlerMap = new HashMap<String, Object>();
		//
		// try {
		// // handlerIteam.split(":");
		// handler = (Handler)
		// app.getBean(handlerIteam.split(":")[0].toUpperCase());
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// throw new RuntimeException("找不到handler:" +
		// handlerIteam.split(":")[0].toUpperCase());
		// }
		// handlerMap.put("handler", handler);
		// handlerMap.put("index", i);
		// handlerMap.put("config", handlerIteam.split(":").length > 1 ?
		// handlerIteam.split(":")[1] : null);
		// handlerInstanceList.add(handlerMap);
		// }

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
		return getHandlerByHandlerList(handlerlist);
	}

	@SuppressWarnings("unchecked")
	public List<Config> getHandlerByHandlerList(List<Map<String,Object>> handlerlist) {
		List<Config> handlerInstanceList = new ArrayList<Config>();

		for (int i = 0; i < handlerlist.size(); i++) {
			Config cfg = this.getHandlerBaseConfig(handlerlist.get(i));
			handlerInstanceList.add(cfg);
		}
		
		return handlerInstanceList;
	}
	
	@SuppressWarnings("unchecked")
	public Config getHandlerBaseConfig(Map<String,Object> config){
		Config cfg = new Config();
		String handlerName = (String) config.get("handler");
		String handlerConfig = (String) config.get("config");
		List<Map<String,Object>> depList = (List<Map<String,Object>>) config.get("dep");

//		System.out.println(handlerName);
		Handler handler = (Handler) app.getBean(handlerName.toUpperCase());
		cfg.setHandler(handler);
		cfg.setHandlerConfig(handlerConfig);
		cfg.setDepList(depList);
//		cfg.setIndex(i);
		return cfg;
	}
}

package com.senyint.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.handler.impl.BranchHandler;
import com.senyint.log.SenyintLog;
import com.senyint.util.ConfigKeyUtils;
import com.senyint.util.JsonUtil;
import com.senyint.util.PropertiesUtils;

@Component
@ConfigurationProperties(prefix = "my")
public class HandlerFactory {

	@Autowired
	ApplicationContext app;

	@Autowired
	Environment env;

	private List<String> servers = new ArrayList<String>();

	public List<String> getServers() {
		return this.servers;
	}

	public void executeHandlerList(List<Config> handlerList, DataStore dataStore) {
		for (int i = 0; i < handlerList.size(); i++) {
			Config cfg = handlerList.get(i);
			dataStore.put(cfg.getHandler().toString(), cfg);
			cfg.getHandler().getClass().getName();
			cfg.getHandler().execute(dataStore);

			if (cfg.getHandler() instanceof BranchHandler) {
				// 执行分支

			}

			if ("true".equals(dataStore.getTempStringData().get("break"))) {
				break;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Config> getHandler(DataStore dataStore) {

		List<Map<String, Object>> handlerList = (List<Map<String, Object>>) dataStore
				.getYaml(ConfigKeyUtils.getServiceHandlerList(dataStore.getRequestCommand()));
		// String handlerListStr =
		// PropertiesUtils.getProperties("task."+methodName + ".handlerlist");
		if (handlerList == null)
			throw new RuntimeException("请检查配置文件:未配置" + dataStore.getRequestCommand() + ".serv.handlerlist");

		// String[] handlerArr = null;
		// try {
		// handlerArr = handlerStr.split(",");
		// } catch (Exception e) {
		// e.printStackTrace();
		// throw new RuntimeException(
		// methodName +
		// ".serv.handlerlist:配置转换数组错误,配置样例qrsqd.serv.list=getdata,conVerTData。Tip:不区分大小写");
		// }

		// List<Map<String, Object>> handlerList =
		// this.convertJsonToList(handlerListStr);
		// System.out.println(handlerList);

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
		return getHandlerListByList(handlerList);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> convertJsonToList(String handlerListStr) {

		List<Map<String, Object>> handlerList = null;
		try {
			while (!handlerListStr.startsWith("[")) {// 不是list的话 就找对应的值
				handlerListStr = PropertiesUtils.getProperties(handlerListStr);
			}
			// map = JsonUtil.json2Map(handlerStr);
			handlerList = JsonUtil.jsonToObject(handlerListStr, List.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			SenyintLog.error(e);
		}
		return handlerList;
	}

	public List<Config> getHandlerListByList(List<Map<String, Object>> handlerlist) {

		List<Config> handlerInstanceList = new ArrayList<Config>();

		for (int i = 0; i < handlerlist.size(); i++) {
			Config cfg = this.getHandlerBaseConfig(handlerlist.get(i));
			handlerInstanceList.add(cfg);
		}

		return handlerInstanceList;
	}

	@SuppressWarnings("unchecked")
	public Config getHandlerBaseConfig(Map<String, Object> config) {
		Config cfg = new Config();
		String handlerName = (String) config.get("handler");
		String handlerConfig = (String) config.get("config");
		List<Map<String, Object>> depList = (List<Map<String, Object>>) config.get("dep");

		// System.out.println(handlerName);
		Handler handler = null;
		try {
			handler = (Handler) app.getBean(handlerName.toUpperCase());
		} catch (Exception e) {
			SenyintLog.error(e);
		}
		cfg.setHandler(handler);
		cfg.setHandlerConfig(handlerConfig);
		cfg.setDepList(depList);
		// cfg.setIndex(i);
		return cfg;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getHandlerList(DataStore dataStore, Object handlerObj) {

		while (handlerObj instanceof String) {
			handlerObj = dataStore.getYaml(ConfigKeyUtils.getHandlerListConfigName((String) handlerObj));
		}

		return (List<Map<String, Object>>) handlerObj;
	}
}

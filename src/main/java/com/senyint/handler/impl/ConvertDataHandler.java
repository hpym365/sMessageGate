package com.senyint.handler.impl;

import org.springframework.stereotype.Component;

import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.handler.BaseHandler;
import com.senyint.handler.Handler;

@Component("CONVERTDATA")
public class ConvertDataHandler extends BaseHandler implements Handler {
	
//	@Autowired
//	HandlerFactory handlerFactory;
//	
//	public void executeDep(List depList){
//		List<Config> configList = handlerFactory.getHandlerByList(depList);
//		for(int i=0;i<configList.size();i++){
//			Config cfg = (Config) configList.get(i);
//			Map map = new HashMap();
//			map.put("config", cfg);
//			cfg.getHandler().execute(map);
//		}
//	}

	@SuppressWarnings("unused")
	public void execute(DataStore dataStore) {
		Config config = this.getConfig(dataStore);
		
		System.out.println("ConvertDataHandler execute");
//		System.out.println(config);
	}
}

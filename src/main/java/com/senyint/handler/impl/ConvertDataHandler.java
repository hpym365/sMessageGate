package com.senyint.handler.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senyint.entity.Config;
import com.senyint.handler.Handler;
import com.senyint.handler.HandlerFactory;

@Component("CONVERTDATA")
public class ConvertDataHandler implements Handler {
	
	@Autowired
	HandlerFactory handlerFactory;
	
	public void executeDep(List depList){
		List<Config> configList = handlerFactory.getHandlerByList(depList);
		for(int i=0;i<configList.size();i++){
			Config cfg = (Config) configList.get(i);
			Map map = new HashMap();
			map.put("config", cfg);
			cfg.getHandler().execute(map);
		}
	}

	public void execute(Map<String, Object> map) {
		Config config = (Config) map.get("config");
		if(config.getDepList()!=null){
			this.executeDep(config.getDepList());
		}
		System.out.println("ConvertDataHandler execute");
		
//		System.out.println(config);
		map.put("ConvertDataHandler是第"+map.get("index")+"次运行的", "ConvertDataHandler操作了!!参数是:"+map.get("config"));
	}
}

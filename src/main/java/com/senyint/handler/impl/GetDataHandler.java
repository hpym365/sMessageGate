package com.senyint.handler.impl;

import org.springframework.stereotype.Component;

import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.handler.BaseHandler;
import com.senyint.handler.Handler;

@Component("GETDATA")
public class GetDataHandler extends BaseHandler implements Handler {
	


	public void execute(DataStore dataStore) {
		Config config = this.getConfig(dataStore);
		
		System.out.println("GetDataHandler execute");
//		map.put("GetDataHandler是第"+map.get("index")+"次运行的", "GetDataHandler操作了!!参数是:"+map.get("config"));
	}
}

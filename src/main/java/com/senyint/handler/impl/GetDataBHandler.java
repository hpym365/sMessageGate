package com.senyint.handler.impl;

import org.springframework.stereotype.Component;

import com.senyint.entity.DataStore;
import com.senyint.handler.BaseHandler;
import com.senyint.handler.Handler;

@Component("GETDATAB")
public class GetDataBHandler extends BaseHandler implements Handler {

	public void execute(DataStore dataStore) {

		System.out.println("GetData BHandler execute");
		// map.put("GetDataHandler是第"+map.get("index")+"次运行的",
		// "GetDataHandler操作了!!参数是:"+map.get("config"));

	}
}

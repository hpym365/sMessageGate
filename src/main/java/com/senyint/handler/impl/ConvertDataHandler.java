package com.senyint.handler.impl;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.senyint.handler.Handler;

@Component("CONVERTDATA")
public class ConvertDataHandler implements Handler {

	public void execute(Map<String, Object> map) {
		System.out.println("ConvertDataHandler execute");
		String config = (String) map.get("config");
		System.out.println(config);
		map.put("ConvertDataHandler是第"+map.get("index")+"次运行的", "ConvertDataHandler操作了!!参数是:"+map.get("config"));
	}
}

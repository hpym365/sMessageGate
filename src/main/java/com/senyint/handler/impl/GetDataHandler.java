package com.senyint.handler.impl;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.senyint.handler.Handler;

@Component("GETDATA")
public class GetDataHandler implements Handler {

	public void execute(Map<String,Object> map) {
		System.out.println("GetDataHandler execute");
		map.put("GetDataHandler", "GetDataHandler操作了");
	}
}

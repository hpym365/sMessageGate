package com.senyint.handler.impl;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.senyint.handler.Handler;

@Component("CONVERTDATA")
public class ConvertDataHandler implements Handler{

	public void execute(Map<String,Object> map) {
		System.out.println("ConvertDataHandler execute");
		map.put("ConvertDataHandler", "ConvertDataHandler操作了");
	}
}

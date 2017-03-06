package com.senyint.handler.impl;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.senyint.handler.Handler;

@Component("TESTB")
public class TestbHandler implements Handler {

	public void execute(Map<String,Object> map) {
		System.out.println("TESTB execute");
		map.put("TESTB是第"+map.get("index")+"次运行的", "TESTB操作了!!参数是:"+map.get("config"));
	}
}

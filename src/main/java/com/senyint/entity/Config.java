package com.senyint.entity;

import java.util.List;

import com.senyint.handler.Handler;

public class Config {
	
	private Handler handler;

	private String handlerConfig;

	private List<String> depList;
	
	private int index;
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public String getHandlerConfig() {
		return handlerConfig;
	}

	public void setHandlerConfig(String handlerConfig) {
		this.handlerConfig = handlerConfig;
	}

	public List<String> getDepList() {
		return depList;
	}

	public void setDepList(List<String> depList) {
		this.depList = depList;
	}

}

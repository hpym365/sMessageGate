package com.senyint.entity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class DataStore extends HashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private Map<String, Object> orginData;

	private Map<String, Object> resultData;

	private InputStream in;

	private HttpServletRequest request;

	private String requestCommand;

	private List<Config> handlerExecuteNav;

	private String streamType;

	public String getStreamType() {
		return streamType;
	}

	public void setStreamType(String streamType) {
		this.streamType = streamType;
	}

	public List<Config> getHandlerExecuteNav() {
		return handlerExecuteNav;
	}

	public void setHandlerExecuteNav(List<Config> handlerExecuteNav) {
		this.handlerExecuteNav = handlerExecuteNav;
	}

	public String getRequestCommand() {
		return requestCommand;
	}

	public void setRequestCommand(String requestCommand) {
		this.requestCommand = requestCommand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Object> getOrginData() {
		return orginData;
	}

	public void setOrginData(Map<String, Object> orginData) {
		this.orginData = orginData;
	}

	public Map<String, Object> getResultData() {
		return resultData;
	}

	public void setResultData(Map<String, Object> resultData) {
		this.resultData = resultData;
	}

	public InputStream getIn() {
		return in;
	}

	public void setIn(InputStream in) {
		this.in = in;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}

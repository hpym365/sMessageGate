package com.senyint.entity;

import java.util.ArrayList;
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

	private Object resultData;
	
	private Map<String, List<Map<String, Object>>> tempData = new HashMap<String, List<Map<String, Object>>>();

	private String streamStr;

	private HttpServletRequest request;

	private String requestCommand;

	private List<Config> handlerExecuteNav;

	private String streamType;

	private String encoding;
	private String dataTag;
	
	public Map<String, List<Map<String, Object>>> getTempData() {
		return tempData;
	}

	public void setTempData(Map<String, List<Map<String, Object>>> tempData) {
		this.tempData = tempData;
	}

	public String getDataTag() {
		return dataTag;
	}

	public void setDataTag(String dataTag) {
		this.dataTag = dataTag;
	}

//	private List<List<Map<String, Object>>> selectList;
//
//	public List<List<Map<String, Object>>> getSelectList() {
//		return selectList;
//	}
//
//	public void addSelectList(List<Map<String, Object>> selectList) {
//		if (this.selectList == null)
//			this.selectList = new ArrayList<List<Map<String, Object>>>();
//
//		this.selectList.add(selectList);
//	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getStreamStr() {
		return streamStr;
	}

	public void setStreamStr(String streamStr) {
		this.streamStr = streamStr;
	}

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


	public Object getResultData() {
		return resultData;
	}

	public void setResultData(Object resultData) {
		this.resultData = resultData;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}

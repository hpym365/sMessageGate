package com.senyint.entity;

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

	private Map<String, Object> orginData = new HashMap<String, Object>();
	private Map<String, String> tempStringData = new HashMap<String, String>();

	private Object input;

	public Map<String, String> getTempStringData() {
		return tempStringData;
	}

	public void setTempStringData(Map<String, String> tempStringData) {
		this.tempStringData = tempStringData;
	}

	private Object resultData;

	private Map<String, Object> tempData = new HashMap<String, Object>();

	private String streamStr;

	private String requestCommand;

	private List<Config> handlerExecuteNav;

	private String streamType;

	private String encode;
	private String dataTag;

	private Object outResult;

	public Object getOutResult() {
		return outResult;
	}

	public void setOutResult(Object outResult) {
		this.outResult = outResult;
	}

	private Map<String, Object> yaml;

	public Map<String, Object> getYaml() {
		return yaml;
	}

	@SuppressWarnings("rawtypes")
	public Object getYaml(String key) {
		String[] pre = key.split("\\.");
		// System.out.println(key);
		Object temp = yaml;
		for (int i = 0; i < pre.length; i++) {
			temp = ((Map) temp).get(pre[i]);
			if (temp == null)
				return "";
		}
		return temp;
	}

	public void setYaml(Map<String, Object> yaml) {
		this.yaml = yaml;
	}

	public Map<String, Object> getTempData() {
		return tempData;
	}

	public void setTempData(Map<String, Object> tempData) {
		this.tempData = tempData;
	}

	public String getDataTag() {
		return dataTag;
	}

	public void setDataTag(String dataTag) {
		this.dataTag = dataTag;
	}

	// private List<List<Map<String, Object>>> selectList;
	//
	// public List<List<Map<String, Object>>> getSelectList() {
	// return selectList;
	// }
	//
	// public void addSelectList(List<Map<String, Object>> selectList) {
	// if (this.selectList == null)
	// this.selectList = new ArrayList<List<Map<String, Object>>>();
	//
	// this.selectList.add(selectList);
	// }

	public String getEncode() {
		if (encode == null)
			return "UTF-8";
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
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

	public Object getInput() {
		return input;
	}

	public void setInput(Object input) {
		this.input = input;
	}

}

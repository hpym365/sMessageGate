package com.senyint.entity;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.senyint.handler.Handler;

public class Config {

	private Handler handler;

	private String handlerConfig;

	private List<Map<String, Object>> depList;

	private int index;

	private String sqlType;
	private String scriptType;
	private String dataSource;
	private String scriptFile;
	private String funName;
	private String dataTag;
	private String templateFileName;
	private String url;
	private String nameSpace;
	private String methodName;
	private String paramName;
	private String getTempDataKey;
	private String saveTempDataKey;
	private String encode;

	private String saveOrginDataKey;

	public String getSaveOrginDataKey() {
		return saveOrginDataKey;
	}

	public void setSaveOrginDataKey(String saveOrginDataKey) {
		this.saveOrginDataKey = saveOrginDataKey;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public String getGetTempDataKey() {
		return getTempDataKey;
	}

	public void setGetTempDataKey(String getTempDataKey) {
		this.getTempDataKey = getTempDataKey;
	}

	public String getSaveTempDataKey() {
		return saveTempDataKey;
	}

	public void setSaveTempDataKey(String saveTempDataKey) {
		this.saveTempDataKey = saveTempDataKey;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getTemplateFileName() {
		return templateFileName;
	}

	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
	}

	public String getDataTag() {
		return dataTag;
	}

	public void setDataTag(String dataTag) {
		this.dataTag = dataTag;
	}

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public String getSqlType() {
		return sqlType;
	}

	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}

	public String getScriptType() {
		return scriptType;
	}

	public void setScriptType(String scriptType) {
		this.scriptType = scriptType;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getScriptFile() {
		return scriptFile;
	}

	public void setScriptFile(String scriptFile) {
		this.scriptFile = scriptFile;
	}

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

	public List<Map<String, Object>> getDepList() {
		return depList;
	}

	public void setDepList(List<Map<String, Object>> depList) {
		this.depList = depList;
	}

}

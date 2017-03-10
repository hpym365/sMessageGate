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

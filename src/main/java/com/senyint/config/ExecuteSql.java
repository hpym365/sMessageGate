package com.senyint.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.senyint.entity.Config;
import com.senyint.entity.DataStore;

@Component
public class ExecuteSql {

	@Autowired
	Environment env;

	Logger logger = Logger.getLogger(this.getClass());

	public void executeSql(DataStore dataStore, JdbcTemplate jdbc, String sqlType, String sql) {

		switch (sqlType) {
		default:
			dataStore.addSelectList(this.querySql(jdbc, sql));
		case "insert":
			this.querySql(jdbc, sql);
		case "update":
			this.querySql(jdbc, sql);
		case "delete":
			this.querySql(jdbc, sql);
		}

	}

	public List<Map<String, Object>> querySql(JdbcTemplate jdbc, String sql) {
		return jdbc.queryForList(sql);
	}
}

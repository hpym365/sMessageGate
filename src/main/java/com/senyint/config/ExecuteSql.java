package com.senyint.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.senyint.entity.DataStore;

@Component
public class ExecuteSql {

	Logger logger = Logger.getLogger(this.getClass());

	public List<Map<String, Object>> executeSql(DataStore dataStore, JdbcTemplate jdbc, String sql) {
//		List<Map<String, Object>> reslist = new ArrayList<Map<String, Object>>();
		if (sql.startsWith("select")) {
			return this.querySql(jdbc, sql);
//			dataStore.addSelectList(this.querySql(jdbc, sql));
		} else if (sql.startsWith("insert")) {
			this.executeSql(jdbc, sql);
			return null;
		} else if (sql.startsWith("update")) {
			this.executeSql(jdbc, sql);
			return null;
		} else if (sql.startsWith("delete")) {
			this.executeSql(jdbc, sql);
			return null;
		}
		throw new RuntimeException("sql语句错误 请检查拼的sql，不是select insert update delete开头的");

		// switch (sqlType) {
		// default:
		//
		// case "insert":
		// this.querySql(jdbc, sql);
		// case "update":
		// this.querySql(jdbc, sql);
		// case "delete":
		// this.querySql(jdbc, sql);
		// }

	}

	public List<Map<String, Object>> querySql(JdbcTemplate jdbc, String sql) {
		return jdbc.queryForList(sql);
	}

	public void executeSql(JdbcTemplate jdbc, String sql) {
		jdbc.execute(sql);
	}
}

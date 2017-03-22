package com.senyint.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.senyint.entity.DataStore;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

@Component
public class ExecuteSql {

	Logger logger = Logger.getLogger(this.getClass());

	@SuppressWarnings({ "unchecked", "restriction" })
	public void executeSql(DataStore dataStore, JdbcTemplate jdbc, Map<String, Object> sqlMap) {
		String sql = (String) sqlMap.get("sql");
		logger.debug("即将要执行的sql语句:" + sql);
		// List<Map<String, Object>> reslist = new ArrayList<Map<String,
		// Object>>();
		if (sql.startsWith("select")) {
			ScriptObjectMirror som = (ScriptObjectMirror) sqlMap.get("param");
			Collection<Object> param = som.values();
			List<Map<String, Object>> res = this.querySql(jdbc, sql, param.toArray());
			if (res != null) {
				Map<String, Object> tempData = dataStore.getTempData();
				// 获取指定的key
				List<Map<String, Object>> keyList = (List<Map<String, Object>>) tempData.get(sqlMap.get("key"));
				if (keyList == null)
					keyList = new ArrayList<Map<String, Object>>();
				keyList.addAll(res);
				tempData.put((String) sqlMap.get("key"), keyList);
				dataStore.setTempData(tempData);
			}
			// dataStore.addSelectList(this.querySql(jdbc, sql));
		} else if (sql.startsWith("insert")) {
			this.executeSql(jdbc, sql);
		} else if (sql.startsWith("update")) {
			this.executeSql(jdbc, sql);
		} else if (sql.startsWith("delete")) {
			this.executeSql(jdbc, sql);
		} else {
			throw new RuntimeException("sql语句错误 请检查拼的sql，不是select insert update delete开头的");
		}

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

	public List<Map<String, Object>> querySql(JdbcTemplate jdbc, String sql, Object[] args) {
		// return jdbc.queryForList(sql);
		return jdbc.queryForList(sql, args);
	}

	public void executeSql(JdbcTemplate jdbc, String sql) {
		jdbc.execute(sql);
	}
}

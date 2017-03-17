package com.senyint.component;

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

	public void executeSql(DataStore dataStore, JdbcTemplate jdbc, Map<String, String> sqlMap) {
		String sql = sqlMap.get("sql");
		logger.debug("即将要执行的sql语句:"+sql);
		// List<Map<String, Object>> reslist = new ArrayList<Map<String,
		// Object>>();
		if (sql.startsWith("select")) {
			List<Map<String, Object>> res = this.querySql(jdbc, sqlMap.get("sql"));
			if (res != null) {
				Map<String, List<Map<String, Object>>> tempData = dataStore.getTempData();
				// 获取指定的key
				List<Map<String, Object>> keyList = tempData.get(sqlMap.get("key"));
				if (keyList == null)
					keyList = new ArrayList<Map<String, Object>>();
				keyList.addAll(res);
				tempData.put(sqlMap.get("key"), keyList);
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

	public List<Map<String, Object>> querySql(JdbcTemplate jdbc, String sql) {
		return jdbc.queryForList(sql);
	}

	public void executeSql(JdbcTemplate jdbc, String sql) {
		jdbc.execute(sql);
	}
}

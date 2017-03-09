package com.senyint.handler.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.handler.BaseHandler;
import com.senyint.handler.Handler;
import com.senyint.test.DynamicDataSource;
import com.senyint.util.GroovyUtils;

/*
 */
@Component("DATABASE")
public class DatabaseHandler extends BaseHandler implements Handler {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	DynamicDataSource dds;

	JdbcTemplate jdbc;

	@Autowired
	Environment env;

	@Override
	public void execute(DataStore dataStore) {
		Config config = this.getConfig(dataStore);// 当前handler的配置

		String handlerConfig = config.getHandlerConfig();

		// 读取配置增删查改?
		String sqlType = env.getProperty(handlerConfig + ".type");
		String script = env.getProperty(handlerConfig + ".script");
		String dataSource = env.getProperty(handlerConfig + ".dataSource");

		jdbc = dds.getJdbcTemplate(dataSource);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "张三");
		map.put("age", 20);
	//	Object res = GroovyUtils.runGroovyScriptByFile(null, script, "hello", new Map[] { map });
//		dataStore.getOrginData().get("DATA");
		Object res = GroovyUtils.runGroovyScriptByFile(null, script, "hello", new Map[] { (Map)dataStore.getOrginData().get("DATA") });

		// TODO Auto-generated method stub

		List<Map<String, Object>> queryForList = jdbc.queryForList(res.toString());
		
		System.out.println(queryForList);
	}

	public void executeSql(DataStore dataStore, String sqlType, String sql) {

		switch (sqlType) {
		default:
			dataStore.addSelectList(this.selectSql(sql));
		case "insert":
			this.selectSql(sql);
		case "update":
			this.selectSql(sql);
		case "delete":
			this.selectSql(sql);
		}

	}

	public List<Map<String, Object>> selectSql(String sql) {
		return jdbc.queryForList(sql);
	}

}

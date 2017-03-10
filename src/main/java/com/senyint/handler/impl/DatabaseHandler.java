package com.senyint.handler.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.senyint.config.DynamicDataSource;
import com.senyint.config.ExecuteSql;
import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.handler.BaseHandler;
import com.senyint.handler.Handler;
import com.senyint.util.ScriptUtils;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

/*
 */
@Component("DATABASE")
public class DatabaseHandler extends BaseHandler implements Handler {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	ExecuteSql exec;

	@Override
	// @Autowired
	public void execute(DataStore dataStore) {
		Config config = this.getConfig(dataStore);// 当前handler的配置

		Object[] params = { dataStore.getOrginData().get("DATA") };

		this.runScript(dataStore, config, params);

		// Object res = GroovyUtils.runGroovyScriptByFile(null, script, "hello",
		// new Map[] { map });
		// dataStore.getOrginData().get("DATA");

		System.out.println(dataStore.getSelectList());
	}

	public void runScript(DataStore dataStore, Config config, Object[] params) {
		try {
			Object sqlRes = this.runScriptByConfig(config, params);
			if (sqlRes instanceof Collection) {
				@SuppressWarnings("unchecked")
				List<String> sqlList = (List<String>) sqlRes;

				sqlList.forEach(sql -> {
					exec.executeSql(dataStore, config.getJdbcTemplate(), config.getSqlType(), sql);
				});
			} else if (sqlRes instanceof String) {
				String sql = (String) sqlRes;
				exec.executeSql(dataStore, config.getJdbcTemplate(), config.getSqlType(), sql);
			} else {
				logger.error(sqlRes);
				throw new RuntimeException("当前执行脚本" + config.getScriptFile() + "的返回类型应为List或String(执行一条或多条sql)");
			}
		} catch (Exception e) {
			logger.error("当前执行脚本" + config.getScriptFile() + "的返回类型应为List或String(执行一条或多条sql)");
			e.printStackTrace();
			// throw new RuntimeException("当前执行脚本" + config.getScriptFile() +
			// "的返回类型应为List或String(执行一条或多条sql)");
			// e.printStackTrace();
		}
	}

	public Object runScriptByConfig(Config config, Object[] params) {
		Object res = null;
		if ("groovy".equals(config.getScriptType())) {
			res = ScriptUtils.runGroovyScriptByFile(null, config.getScriptFile(), config.getFunName(), params);
		} else {
			res = ScriptUtils.runJavaScriptByFile(null, config.getScriptFile(), config.getFunName(), params);
		}
		if (res instanceof ScriptObjectMirror) {
			return ((ScriptObjectMirror) res).values();
		}

		return res;
	}
}

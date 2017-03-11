package com.senyint.config;

import java.util.Collection;
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
import com.senyint.util.ScriptUtils;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

@Component
public class ScriptEngine {

	@Autowired
	Environment env;

	@Autowired
	ExecuteSql exec;

	Logger logger = Logger.getLogger(this.getClass());

	public void runScriptExecSql(DataStore dataStore, String scriptType, String scriptFile, String funName,
			JdbcTemplate jdbc, Object[] params) {
		try {
			Object sqlRes = this.runScriptByConfig(scriptType, scriptFile, funName, params);
			if (sqlRes instanceof Collection) {
				@SuppressWarnings("unchecked")
				List<String> sqlList = (List<String>) sqlRes;

				sqlList.forEach(sql -> {
					exec.executeSql(dataStore, jdbc, sql);
				});
			} else if (sqlRes instanceof String) {
				String sql = (String) sqlRes;
				exec.executeSql(dataStore, jdbc, sql);
			} else {
				logger.error(sqlRes);
				throw new RuntimeException("当前执行脚本" + scriptFile + "的返回类型应为List或String(执行一条或多条sql)");
			}
		} catch (Exception e) {
			logger.error("当前执行脚本" + scriptFile + "的返回类型应为List或String(执行一条或多条sql)");
			e.printStackTrace();
			// throw new RuntimeException("当前执行脚本" + config.getScriptFile() +
			// "的返回类型应为List或String(执行一条或多条sql)");
			// e.printStackTrace();
		}
	}

	public Object runScriptByConfig(String scriptType, String scriptFile, String funName, Object[] params) {
		Object res = null;
		if ("groovy".equals(scriptType)) {
			res = ScriptUtils.runGroovyScriptByFile(null, scriptFile, funName, params);
		} else if ("javascript".equals(scriptType)) {
			res = ScriptUtils.runJavaScriptByFile(null, scriptFile, funName, params);
		} else {
			throw new RuntimeException("scriptType类型错误");
		}
		if (res instanceof ScriptObjectMirror) {
			return ((ScriptObjectMirror) res).values();
		}

		return res;
	}

}

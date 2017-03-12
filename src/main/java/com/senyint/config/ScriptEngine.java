package com.senyint.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.senyint.entity.DataStore;
import com.senyint.util.ScriptUtils;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

@Component
public class ScriptEngine {

	@Autowired
	ExecuteSql exec;

	Logger logger = Logger.getLogger(this.getClass());

	public Object runScriptConvertData(String scriptType, String scriptFile, String funName, Object[] params) {
		try {
			return this.runScriptByConfig(scriptType, scriptFile, funName, params);
		} catch (Exception e) {
			logger.error("当前执行脚本" + scriptFile + "执行出错，请检查文件！");
			e.printStackTrace();
			// throw new RuntimeException("当前执行脚本" + config.getScriptFile() +
			// "的返回类型应为List或String(执行一条或多条sql)");
			// e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public void runScriptExecSql(DataStore dataStore, String scriptType, String scriptFile, String funName,
			JdbcTemplate jdbc, Object[] params) {
		try {
			Object sqlRes = this.runScriptByConfig(scriptType, scriptFile, funName, params);
			if (sqlRes instanceof Collection) {
				@SuppressWarnings("unchecked")
				List<Map<String, String>> sqlList = (List<Map<String, String>>) sqlRes;
				sqlList.forEach(sqlMap -> {
					exec.executeSql(dataStore, jdbc,sqlMap);
				});
			} else if (sqlRes instanceof Map) {
				exec.executeSql(dataStore, jdbc,(Map<String, String>) sqlRes);
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

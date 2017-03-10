package com.senyint.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import com.senyint.config.DynamicDataSource;
import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.handler.impl.DatabaseHandler;

public class BaseHandler implements Handler {

	@Autowired
	Environment env;

	@Autowired
	DynamicDataSource dds;

	public Config getConfig(DataStore dataStore) {
		Config config = (Config) dataStore.get(this.toString());
		// 如果有db配置 和js的配置 那么读取 dbhandler才读取这些
		if (config.getHandlerConfig() != null && config.getHandler() instanceof DatabaseHandler) {
			String handlerConfig = config.getHandlerConfig();

			// 读取配置增删查改? 脚本类型 数据源等
			String sqlType = env.getProperty(handlerConfig + ".sqlType");
			String scriptType = env.getProperty(handlerConfig + ".scriptType");
			String dataSource = env.getProperty(handlerConfig + ".dataSource");
			String scriptFile = env.getProperty(handlerConfig + ".scriptFile");
			String funName = env.getProperty(handlerConfig + ".funName");

			config.setSqlType(sqlType);
			config.setScriptType(scriptType == null ? "javascript" : scriptType);
			config.setDataSource(dataSource);
			config.setScriptFile(scriptFile);
			config.setFunName(funName);

			JdbcTemplate jdbcTemplate = dds.getJdbcTemplate(config.getDataSource());

			config.setJdbcTemplate(jdbcTemplate);
		}
		return config;
	}

	@Override
	public void execute(DataStore dataStore) {
		// TODO Auto-generated method stub
	}

}

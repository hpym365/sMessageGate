package com.senyint.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.senyint.component.DynamicDataSource;
import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.handler.impl.ConvertDataHandler;
import com.senyint.handler.impl.DatabaseHandler;
import com.senyint.util.PropertiesUtils;

public class BaseHandler implements Handler {

	@Autowired
	DynamicDataSource dds;

	public Config getConfig(DataStore dataStore) {

		return this.getHandlerDetailConfig((Config) dataStore.get(this.toString()));
	}

	public Config getHandlerDetailConfig(Config config) {
		String handlerConfig = config.getHandlerConfig();

		// 如果有db配置 和js的配置 那么读取 dbhandler才读取这些
		if (config.getHandlerConfig() != null && (config.getHandler() instanceof DatabaseHandler || config.getHandler() instanceof ConvertDataHandler)) {

			// 读取配置增删查改? 脚本类型 数据源等
			String sqlType = PropertiesUtils.getProperties(handlerConfig + ".sqlType");
			String scriptType = PropertiesUtils.getProperties(handlerConfig + ".scriptType");
			String dataSource = PropertiesUtils.getProperties(handlerConfig + ".dataSource");
			String scriptFile = PropertiesUtils.getProperties(handlerConfig + ".scriptFile");
			String funName = PropertiesUtils.getProperties(handlerConfig + ".funName");

			config.setSqlType(sqlType);
			config.setScriptType(scriptType == "" ? "javascript" : scriptType);
			config.setDataSource(dataSource);
			config.setScriptFile(scriptFile);
			config.setFunName(funName);

			JdbcTemplate jdbcTemplate = dds.getJdbcTemplate(config.getDataSource());

			config.setJdbcTemplate(jdbcTemplate);
		}

		return config;
	}

	@Override
	public String execute(DataStore dataStore) {
		// TODO Auto-generated method stub
	}
	
	

}

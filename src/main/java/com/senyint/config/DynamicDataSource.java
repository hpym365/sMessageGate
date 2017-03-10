package com.senyint.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DynamicDataSource {

	@Autowired
	Environment env;

	Logger logger = Logger.getLogger(this.getClass());
	
	private Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();
	private Map<String, JdbcTemplate> jdbcTemplateMap = new HashMap<String, JdbcTemplate>();

	private DataSource getDynamicDataSource(String dsName) {

		if (dataSourceMap != null && dataSourceMap.get(dsName) != null) {
			logger.debug("缓存数据源  dataSourceMap 命中并返回"+dsName);
			return dataSourceMap.get(dsName);
		}

		String url = env.getProperty("datasource." + dsName + ".url");
		String driverClassName = env.getProperty("datasource." + dsName + ".driver");
		String username = env.getProperty("datasource." + dsName + ".username");
		String password = env.getProperty("datasource." + dsName + ".password");

		DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
				.username(username).password(password);
		logger.debug("创建了数据源  "+dsName);
		//
		// if (dataSourceMap == null) {
		// dataSourceMap = new HashMap<String, DataSource>();
		// }

		dataSourceMap.put(dsName, factory.build());

		return factory.build();
	}

	public JdbcTemplate getJdbcTemplate(String dsName) {

		if (jdbcTemplateMap != null && jdbcTemplateMap.get(dsName) != null) {
			logger.debug("缓存数据源  jdbcTemplateMap 命中并返回"+dsName);
			return jdbcTemplateMap.get(dsName);
		}
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDynamicDataSource(dsName));
		jdbcTemplateMap.put(dsName, jdbcTemplate);

		return new JdbcTemplate(this.getDynamicDataSource(dsName));
	}
}

package com.senyint.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.senyint.component.DynamicDataSource;
import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.log.SenyintLog;
import com.senyint.util.ConfigKeyUtils;

public class BaseHandler implements Handler {

	@Autowired
	DynamicDataSource dds;

	public Config getConfig(DataStore dataStore) {

		try {
			return this.getHandlerDetailConfig(dataStore);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			SenyintLog.error(e);
		}
		return null;
	}

	public Config getHandlerDetailConfig(DataStore dataStore) throws Exception {
		Config config = (Config) dataStore.get(this.toString());
		String configName = config.getHandlerConfig();

		// 如果有db配置 和js的配置 那么读取 dbhandler才读取这些
		// if (config.getHandlerConfig() != null && (config.getHandler()
		// instanceof DatabaseHandler
		// || config.getHandler() instanceof ConvertDataHandler)) {

		String encode = (String) dataStore.getYaml(ConfigKeyUtils.getHandlerConfigEncode(configName));
		config.setEncode(encode == "" ? "UTF-8" : encode);
		// 读取配置增删查改? 脚本类型 数据源等
		if (configName != null) {
			String scriptType = (String) dataStore.getYaml(ConfigKeyUtils.getHandlerConfigScriptType(configName));
			String dataSource = (String) dataStore.getYaml(ConfigKeyUtils.getHandlerDataSource(configName));
			String scriptFile = (String) dataStore.getYaml(ConfigKeyUtils.getHandlerConfigScriptName(configName));
			String funName = (String) dataStore.getYaml(ConfigKeyUtils.getHandlerConfigFunName(configName));
			String templateName = (String) dataStore.getYaml(ConfigKeyUtils.getHandlerTemplateName(configName));

			config.setScriptType(scriptType == "" ? "javascript" : scriptType);
			config.setDataSource(dataSource);
			config.setScriptFile(scriptFile);
			config.setFunName(funName);
			config.setTemplateFileName(templateName);

			JdbcTemplate jdbcTemplate = dds.getJdbcTemplate(config.getDataSource());

			config.setJdbcTemplate(jdbcTemplate);

			String url = (String) dataStore.getYaml(ConfigKeyUtils.getHandlerConfigWebServiceUrl(configName));
			String nameSpace = (String) dataStore
					.getYaml(ConfigKeyUtils.getHandlerConfigWebServiceNameSpace(configName));
			String methodName = (String) dataStore
					.getYaml(ConfigKeyUtils.getHandlerConfigWebServiceMethodName(configName));
			String paramName = (String) dataStore
					.getYaml(ConfigKeyUtils.getHandlerConfigWebServiceParamName(configName));
			String getTempDataKey = (String) dataStore
					.getYaml(ConfigKeyUtils.getHandlerConfigGetTempDataKey(configName));

			String saveTempDataKey = (String) dataStore
					.getYaml(ConfigKeyUtils.getHandlerConfigSaveTempDataKey(configName));

			config.setUrl(url);
			config.setNameSpace(nameSpace);
			config.setMethodName(methodName);
			config.setParamName(paramName == "" ? "arg0" : paramName);
			config.setGetTempDataKey(getTempDataKey);
			config.setSaveTempDataKey(saveTempDataKey);

			String saveOrginDataKey = (String) dataStore
					.getYaml(ConfigKeyUtils.getHandlerConfigSaveOrginDataKey(configName));
			config.setSaveOrginDataKey(saveOrginDataKey == "" ? "default" : saveOrginDataKey);
		}
		return config;
	}

	@Override
	public void execute(DataStore dataStore) {
	}

}

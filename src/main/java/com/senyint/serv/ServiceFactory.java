package com.senyint.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.senyint.entity.DataStore;
import com.senyint.util.ConfigKeyUtils;

/**
 * @author hpym365
 * @version 创建时间：2017年2月28日 上午1:24:1 类说明
 */
@Component
public class ServiceFactory {

	@Autowired
	ApplicationContext app;

	Serv serv;

	public Serv getServ(DataStore dataStore) {
		String servName = (String) dataStore.getYaml(ConfigKeyUtils.getServiceName(dataStore.getRequestCommand()));
		// String servName =
		// PropertiesUtils.getProperties(dataStore.getRequestCommand() +
		// ".serv","default");

		try {
			serv = (Serv) app.getBean(servName);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("找不到serv:" + servName);
		}

		return serv;
	}
}

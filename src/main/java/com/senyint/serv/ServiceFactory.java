package com.senyint.serv;

import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.senyint.entity.DataStore;
import com.senyint.entity.SystemConstants;
import com.senyint.util.YamlUtil;

/**
 * @author hpym365
 * @version 创建时间：2017年2月28日 上午1:24:1 类说明
 */
@Component
public class ServiceFactory {

	@Autowired
	Environment env;

	@Autowired
	ApplicationContext app;

	Serv serv;

	public Serv getServ(DataStore dataStore) {
		
		String servName = env.getProperty(dataStore.getRequestCommand() + ".serv");

		try {
			serv = (Serv) app.getBean(servName);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("找不到serv:" + servName);
		}

		return serv;
	}
}

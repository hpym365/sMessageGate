package com.senyint.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author hpym365
 * @version 创建时间：2017年2月28日 上午1:24:10 类说明
 */
@Component
public class ServiceFactory {

	@Autowired
	Environment env;

	@Autowired
	ApplicationContext app;

	BaseServ serv;

	public BaseServ getHandler(String servMethodName) {

		String servName = env.getProperty(servMethodName + ".serv");

		try {
			serv = (BaseServ) app.getBean(servName);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("找不到serv:" + servName);
		}

		return serv;
	}
}

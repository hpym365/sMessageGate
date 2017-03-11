package com.senyint.serv.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.entity.SystemConstants;
import com.senyint.serv.Serv;

/**
 * @author hpym365
 * @version 创建时间：2017年2月28日 上午1:22:50 类说明
 */
@Component("default")
public class DefaultServ extends BaseServ implements Serv {

	public void init(DataStore dataStore) {
		System.out.println("DefaultServ start...execute handlers..");
		this.executeHandlerList(dataStore);

		List<Config> list = dataStore.getHandlerExecuteNav();
		for (Config cfg : list) {
			System.out.println(cfg.getHandler());
		}
	}
}
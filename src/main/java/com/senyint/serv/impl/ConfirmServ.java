package com.senyint.serv.impl;

import org.springframework.stereotype.Component;

import com.senyint.entity.DataStore;
import com.senyint.serv.Serv;

/**
 * @author hpym365
 * @version 创建时间：2017年2月28日 上午1:22:50 类说明
 */
@Component("confirm")
public class ConfirmServ extends BaseServ implements Serv {

	public void init(DataStore dataStore) {
		System.out.println("ConfirmServ start...execute handlers..");
//		map.put("begin", "我开始执行了");
		this.executeHandlerList(dataStore);
	}
}
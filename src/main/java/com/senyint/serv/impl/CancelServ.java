package com.senyint.serv.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.senyint.serv.Serv;

/**
 * @author hpym365
 * @version 创建时间：2017年2月28日 上午1:22:50 类说明
 */
@Component("cancel")
public class CancelServ extends BaseServ implements Serv {

	public void init(Map<String,Object> map) {
		System.out.println("ConfirmServ start...execute handlers..");
		this.executeHandlerList(map);
	}
}
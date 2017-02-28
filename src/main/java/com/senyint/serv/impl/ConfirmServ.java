package com.senyint.serv.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.senyint.handler.Handler;
import com.senyint.handler.HandlerFactory;
import com.senyint.serv.Serv;

/**
 * @author hpym365
 * @version 创建时间：2017年2月28日 上午1:22:50 类说明
 */
@Component("confirm")
public class ConfirmServ extends BaseServ implements Serv  {

	public void init(String methodName) {
		System.out.println("ConfirmServ start...execute handlers..");
		this.executeHandlerList(methodName);
	}
}
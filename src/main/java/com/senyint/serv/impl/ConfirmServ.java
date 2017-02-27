package com.senyint.serv.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.senyint.serv.BaseServ;

/**
 * @author hpym365
 * @version 创建时间：2017年2月28日 上午1:22:50 类说明
 */
@Component("confirm")
public class ConfirmServ implements BaseServ {

	public void init() {
		System.out.println("ConfirmServ start...execute handlers..");
	}
}
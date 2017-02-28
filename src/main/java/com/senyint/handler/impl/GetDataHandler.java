package com.senyint.handler.impl;

import org.springframework.stereotype.Component;

import com.senyint.handler.Handler;

@Component("GETDATA")
public class GetDataHandler implements Handler {

	public void execute() {
		System.out.println("GetDataHandler execute");
	}
}

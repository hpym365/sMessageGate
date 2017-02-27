package com.senyint.handler.impl;

import org.springframework.stereotype.Component;

import com.senyint.handler.Handler;

@Component
public class ConvertDataHandler implements Handler{

	public void execute() {
		System.out.println("ConvertDataHandler execute");
	}
}

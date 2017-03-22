package com.senyint.test.webservice;

public class MyServiceImpl implements IMyService {

	@Override
	public Integer add(Integer num1, Integer num2) {
		// TODO Auto-generated method stub
		return num1 + num2;
	}

	@Override
	public Integer minus(Integer num1, Integer num2) {
		// TODO Auto-generated method stub
		return num1 - num2;
	}

	@Override
	public String sayHello(String says) {
		// TODO Auto-generated method stub
		System.out.println("接收到的信息为:" + says);
		return "服务端返回信息:" + says;
	}

}

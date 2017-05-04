package com.senyint.test.webservice;

import javax.xml.ws.Endpoint;

public class MyService {

	public static void main(String[] args) {
		String address = "http://localhost:8888/webservice";
//		Endpoint.publish(address, new MyServiceImpl());
		Endpoint.publish(address, new Test());
	}
}

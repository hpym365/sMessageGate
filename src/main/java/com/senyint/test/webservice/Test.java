package com.senyint.test.webservice;

import javax.jws.WebService;

@WebService(targetNamespace="http://serv.senyint.com")
public class Test {

	public Object sayHello(String says) {
		return says;
	}
}

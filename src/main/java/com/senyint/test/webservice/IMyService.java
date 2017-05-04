package com.senyint.test.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IMyService {

	// @WebResult(name="addResult")
	@WebMethod
	public Integer add(Integer num1, Integer num2);// @WebParam(name="num1")

	@WebMethod
	public Integer minus(Integer num1, Integer num2);

	@WebMethod
	public String sayHello(String says);
	
	@WebMethod
	public String sayHello2(String says);
}
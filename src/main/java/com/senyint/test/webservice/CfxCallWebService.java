package com.senyint.test.webservice;

import javax.xml.namespace.QName;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class CfxCallWebService {

	public static void main(String[] args) throws Exception {
		// 这个是用cxf 客户端访问cxf部署的webservice服务
		// 千万记住，访问cxf的webservice必须加上namespace ,否则通不过
		// 现在又另外一个问题，传递过去的参数服务端接收不到
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		org.apache.cxf.endpoint.Client client = dcf.createClient("http://localhost:8080/soap/hello?wsdl");
		// url为调用webService的wsdl地址
		QName name = new QName("http://webservice.test.senyint.com/", "sayHello");
		// namespace是命名空间，methodName是方法名
		String xmlStr = "测试消息";
		// paramvalue为参数值
		Object[] objects = client.invoke(name, xmlStr);
		// 调用web Service//输出调用结果
		System.out.println(objects[0].toString());
	}
}

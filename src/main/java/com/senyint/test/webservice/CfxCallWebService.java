package com.senyint.test.webservice;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.senyint.util.CallWebServiceUtils;

public class CfxCallWebService {

	public static void main(String[] args) throws Exception {

		long time1 = System.currentTimeMillis();
		for (int i = 0; i < 50; i++) {
			Object result = CallWebServiceUtils.callWebServiceAxis("http://localhost:8080/soap/hello?wsdl",
					"http://webservice.test.senyint.com/", "sayHello", "arg0", new Object[] { "Dear I miss you" });
			System.out.println(result);
		}
		long axis = System.currentTimeMillis() - time1;

		long time2 = System.currentTimeMillis();
		for (int i = 0; i < 50; i++) {
			System.out.println(CallWebServiceUtils.callWebServiceAxis2("http://localhost:8080/soap/hello?wsdl",
					"http://webservice.test.senyint.com/", "sayHello", new Object[] { "Dear I miss you" }));
		}
		long axis2 = System.currentTimeMillis() - time2;

		long time = System.currentTimeMillis();
		for (int i = 0; i < 50; i++) {
			String url = "http://localhost:8080/soap/hello?wsdl";
			String nameSpace = "http://webservice.test.senyint.com/";
			String methodName = "sayHello";
			String message = "hahaha";
			Object[] callWebServiceCxf2 = CallWebServiceUtils.callWebServiceCxf(url, nameSpace, methodName, message);
			System.out.println(callWebServiceCxf2[0]);
		}
		long cxf = System.currentTimeMillis() - time;

		System.out.println("axis" + "总供执行这么久:" + axis);
		System.out.println("axis2" + "总供执行这么久:" + axis2);
		System.out.println("cxf" + "总供执行这么久:" + cxf);

		System.out.println();
		// url = "http://www.webservicex.net/geoipservice.asmx?WSDL";
		// nameSpace = "http://www.webservicex.net/";
		// methodName = "GetGeoIP";
		// message = "127.0.0.1";
		// Object[] callWebServiceCxf = CallWebService.callWebServiceCxf(url,
		// nameSpace, methodName, message);
		// System.out.println(callWebServiceCxf);
		// Object g = callWebServiceCxf[0];
	}

	public Object[] cfxCallWebService(String url, String nameSpace, String methodName, Object... args)
			throws Exception {
		// 这个是用cxf 客户端访问cxf部署的webservice服务
		// 千万记住，访问cxf的webservice必须加上namespace ,否则通不过
		// 现在又另外一个问题，传递过去的参数服务端接收不到
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(url);
		// url为调用webService的wsdl地址
		QName name = new QName(nameSpace, methodName);
		// namespace是命名空间，methodName是方法名
		// paramvalue为参数值
		Object[] objects = client.invoke(name, args);

		// 调用web Service//输出调用结果
		return objects;
	}
}

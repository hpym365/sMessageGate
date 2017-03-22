package com.senyint.test.webservice;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

public class AxisCallWebService {
	public String invokeWebService(String endpoint, String namespaceURI, String method, String paramName, String msg) {
		String result = "no result!";
		Service service = new Service();
		Call call;

		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpoint);// 远程调用路径
			// call.setOperationName("sayHello");// 调用的方法名
			QName qName = new QName(namespaceURI, method);
			call.setOperationName(qName);
			// call.setOperation("sayHello");// 调用的方法名

			// 设置参数名:
			call.addParameter(paramName, // 参数名
					XMLType.XSD_STRING, // 参数类型:String
					ParameterMode.IN);// 参数模式：'IN' or 'OUT'

			// 设置返回值类型：
			call.setReturnType(XMLType.XSD_STRING);// 返回值类型：String

			result = (String) call.invoke(new Object[] { msg });// 远程调用
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		AxisCallWebService t = new AxisCallWebService();
		Object[] param = new Object[1];
		param[0] = "Dear I miss you";// Object是用来存储方法的参数
		String result = t.invokeWebService("http://localhost:8080/soap/hello?wsdl",
				"http://webservice.test.senyint.com/", "sayHello", "arg0", "Dear I miss you");
		System.out.println(result);
	}
}

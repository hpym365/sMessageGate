package com.senyint.util;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * 
 * @ClassName: CallWebServiceUtils
 * @Description: webservice公共调用 客户端接口工具类
 * @author hpym365@gmail.com
 * @date 2017年3月22日 下午11:12:27
 * @version V1.0
 */
public class CallWebServiceUtils2 {

	public static Object[] callWebServiceCxf(String url, String nameSpace, String methodName, Object... args)
			throws Exception {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(url);
		QName name = new QName(nameSpace, methodName);
		Object[] object = client.invoke(name, args);
		return object;
	}

}

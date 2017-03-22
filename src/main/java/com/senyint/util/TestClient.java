package com.senyint.util;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.dataretrieval.client.MexClient;

public class TestClient {
	private static EndpointReference targetEPR = new EndpointReference(
			"http://localhost:8080/soap/hello?wsdl");

	/**
	 * Simple axis2 client.
	 *
	 * @param args
	 *            Main
	 */
	public static void main(String[] args) {
		try {
			OMFactory factory = OMAbstractFactory.getOMFactory();
			OMNamespace omNs = factory.createOMNamespace("http://webservice.test.senyint.com/", "sayHello");

			OMElement method = factory.createOMElement("sayHello", omNs);
			method.addChild(factory.createOMText("123123"));
			ServiceClient serviceClient = new ServiceClient();

			Options options = new Options();

			options.setTo(targetEPR);
			serviceClient.setOptions(options);

			// Blocking invocation
			OMElement result = serviceClient.sendReceive(method);

			StringWriter writer = new StringWriter();
			result.serialize(XMLOutputFactory.newInstance().createXMLStreamWriter(writer));
			writer.flush();

			System.out.println("Response: " + writer.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

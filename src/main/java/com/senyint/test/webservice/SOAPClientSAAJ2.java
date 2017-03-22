package com.senyint.test.webservice;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class SOAPClientSAAJ2 {

	/**
	 * Starting point for the SAAJ - SOAP Client Testing
	 */
	public static void main(String args[]) {
		try {
			// Create SOAP Connection
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();

			// Send SOAP Message to SOAP Server
			String url = "http://webservice.36wu.com/MobilePhoneService.asmx";
			SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);

			// Process the SOAP Response
			printSOAPResponse(soapResponse);

			soapConnection.close();
		} catch (Exception e) {
			System.err.println("Error occurred while sending SOAP Request to Server");
			e.printStackTrace();
		}
	}

	private static SOAPMessage createSOAPRequest() throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();

		String serverURI = "http://webservice.36wu.com/";

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration("example", serverURI);

		/*
		 * <soap12:Envelope
		 * xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		 * xmlns:xsd="http://www.w3.org/2001/XMLSchema"
		 * xmlns:soap12="http://www.w3.org/2003/05/soap-envelope"> <soap12:Body>
		 * <getInfoByMobilePhone xmlns="Yangzhili">
		 * <PhoneNumber>string</PhoneNumber> <UserId>string</UserId>
		 * </getInfoByMobilePhone> </soap12:Body> </soap12:Envelope>
		 */

		// SOAP Body
		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement("PhoneNumber");
		SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("UserId");
		soapBodyElem.addTextNode("13000000000");
		soapBodyElem1.addTextNode("123");

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", serverURI + "getInfoByMobilePhone");

		soapMessage.saveChanges();

		/* Print the request message */
		System.out.print("Request SOAP Message = ");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	/**
	 * Method used to print the SOAP Response
	 */
	private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source sourceContent = soapResponse.getSOAPPart().getContent();
		System.out.print("\nResponse SOAP Message = ");
		FileOutputStream fileOutputStream = new FileOutputStream(new File("d:\\aa.txt"));
		// BufferedOutputStream bos = new BufferedOutputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		StreamResult result = new StreamResult(baos);
		transformer.transform(sourceContent, result);
		System.out.println(baos.toString("UTF-8"));
	}

}

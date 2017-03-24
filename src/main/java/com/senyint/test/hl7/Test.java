package com.senyint.test.hl7;

import java.io.IOException;

import org.jl7.hl7.HL7Message;
import org.jl7.hl7.HL7Parser;
import org.jl7.mllp.MLLPMetaData;
import org.jl7.mllp.MLLPTransport;
import org.jl7.mllp.MLLPTransportable;

public class Test {

//	private static final String MESSAGE = "MSH|^~\\&||ABCHS||AUSDHSV|20070103112951||ADT^A08^ADT_A01|12334456778893|P|2.5|||NE|NE|AU|ASCII\rEVN|A08|20060705000000\rPID|1||0000112234^^^100^A||XXXXXXXXXX^^^^^^S||10131113|1||4|^^RICHMOND^^3121||||1201||||||||1100|||||||||AAA\rPV1|1|O|^^^^^1|||||||2|||||1||||654345509^^^100^A|1|||||||||||||||||||||||||200607050000||||||V\rPV2|||||||1||||||||||||||||^^^^^^^^^103\rROL|1|AD|SAHCP|XXXXXXXXXX^^^^^^S|||||6|1\rPR1|1||1||20060705|1\rGT1|1||||||||||||||||||||NOT APPLICABLE";
	private static final String MESSAGE = "MSH|^~\\&|PACS|PACS|JHIP|MediII|20150118163602||ORU^R01|2960466|P|2.4\rPID||1003343|1003343~1003343~1003343||ChuTingTing^褚婷婷||20030104000000|F|||梅城龙山||^^^^^^0|^^^^^^13082847221||||0|||||||||||||||||||||河南省郑州市|河南省郑州市高新区|北京嘉和美康信息技术有限公司|软件工程师|B\rPV1||O|^^||||209^^||||||||||||1000210046||||||||||||||||||||||||||||||||V\rORC|UX|292201|USY50936||CM||||20150118155700|1212^^王妍||209^^陈杰|10149^^^^^^^^B超室|||^|10070^儿科^|||^|||||F\rOBR|1||USY50936^^938177|173544^腹部（肝、胆、脾、胰）||||||||10149^B超室^1||||||||||20150118163602||US|C|||||||||1212&&王妍|1212&&王妍\rNTE|1||\rOBX|1|CE|IMP||肝大小形态正常，肝包膜光整，肝实质回声分布均匀，肝血管网络显示清晰，正常分布。肝静脉、门静脉内径正常。左右肝管、肝内胆管未见扩张。餐后胆囊缩小。胆总管未见扩张，显示段管腔内未见异常回声。脾大小正常，轮廓光整，实质回声均匀，未见异常回声。胰腺受胃肠道气体干扰显示不清。||||||F\rOBX|2|TX|GDT||肝脾未见明显异常||||||F\rOBX|3|RP|ZMF||||||||F";
	private static final String HOST = "localhost";
	private static int PORT = 9991;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HL7Message msg = HL7Parser.parseMessage(MESSAGE, true);
		
		MLLPTransportable transportable = new MLLPTransportable();
		transportable.message = msg;
		transportable.metaData = new MLLPMetaData(HOST, PORT);
		MLLPTransport transport = new MLLPTransport();
		for (int i = 0; i < 10; i++) {
			try {
				MLLPTransportable tr = transport.sendMessage(transportable, true);
				System.out.println(tr.message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

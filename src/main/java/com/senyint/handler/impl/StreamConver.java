package com.senyint.handler.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.handler.BaseHandler;
import com.senyint.handler.Handler;
import com.senyint.util.MapUtil;

/*
 */
@Component("STREAMCONVERT")
public class StreamConver extends BaseHandler implements Handler{

	Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public void execute(DataStore dataStore) {
		Config config = this.getConfig(dataStore);//当前handler的配置
		// TODO Auto-generated method stub
		try {
			this.convertXmlToMap(dataStore);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unused")
	public void convertXmlToMap(DataStore dataStore){
//		BufferedReader br = new BufferedReader(new InputStreamReader(dataStore.getIn(), "GBK"));
//		String s = "";
//		while(br.readLine()!=null){
//			s=s+br.readLine();
//		}
//		br.readLine();
		InputStream in = dataStore.getIn();
		SAXReader rd = new SAXReader();
		rd.setEncoding("UTF-8");
		Document doc = null;
		try {
//			ByteArrayOutputStream output = new ByteArrayOutputStream();
//					int temp = in.read();
//					while (temp != -1) {
//						output.write(temp);
//						temp = in.read();
//					}
//
//					ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
//					BufferedReader br = new BufferedReader(new InputStreamReader(input, "UTF-8"));	
			BufferedReader reader = new BufferedReader(new InputStreamReader(dataStore.getIn(), "UTF-8"));
			doc = new SAXReader().read(reader);
			reader.reset();
			String streamStr = reader.readLine();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> orginMap = MapUtil.Dom2Map(doc.getRootElement());
		dataStore.setOrginData(orginMap);
		System.out.println(orginMap);
		//config 加入到datastore   然后根据读出来的流 判断什么开始的  如果是{ json  如果是<xml 如果是？？。。。。
	}

}

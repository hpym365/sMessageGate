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
	
	public void convertXmlToMap(DataStore dataStore) throws DocumentException{
		Document doc = new SAXReader().read(new ByteArrayInputStream(dataStore.getStreamStr().getBytes()));
		Map<String, Object> orginMap = MapUtil.Dom2Map(doc.getRootElement());
		dataStore.setOrginData(orginMap);
		System.out.println(orginMap);
		//config 加入到datastore   然后根据读出来的流 判断什么开始的  如果是{ json  如果是<xml 如果是？？。。。。
	}

}

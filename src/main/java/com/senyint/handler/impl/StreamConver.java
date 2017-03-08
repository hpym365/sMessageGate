package com.senyint.handler.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.handler.BaseHandler;
import com.senyint.handler.Handler;

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
			this.convertStream(dataStore);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unused")
	public void convertStream(DataStore dataStore) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(dataStore.getIn(), "GBK"));
//		String s = "";
//		while(br.readLine()!=null){
//			s=s+br.readLine();
//		}
//		br.readLine();
		Document doc = new SAXReader().read(dataStore.getIn());
		System.out.println(doc);
		//config 加入到datastore   然后根据读出来的流 判断什么开始的  如果是{ json  如果是<xml 如果是？？。。。。
	}

}

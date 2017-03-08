package com.senyint.handler.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.handler.BaseHandler;
import com.senyint.handler.Handler;

@Component("RECIVESTREAM")
public class ReciveStreamHandler extends BaseHandler implements Handler {

	Logger logger = Logger.getLogger(this.getClass());

	public void execute(DataStore dataStore) {
		Config config = this.getConfig(dataStore);
		HttpServletRequest request = dataStore.getRequest();
		InputStream in = null;
		try {
			in = this.reciveStream(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataStore.setIn(in);

		String streamType = this.getStreamType(dataStore);
		if(streamType == null)
			throw new RuntimeException("流文件类型检查失败  只支持 xml json");
		
		dataStore.setStreamType(streamType);
		System.out.println("ReciveStreamHandler execute");
	}

	public InputStream reciveStream(HttpServletRequest request) throws IOException {
		InputStream in = request.getInputStream();

		String reAddr = request.getRemoteAddr();
		String reHost = request.getRemoteHost();
		int rePort = request.getRemotePort();
		String reUser = request.getRemoteUser();

		StringBuffer sb = new StringBuffer();
		sb.append("Got request from Address[" + reAddr + "],Host[" + reHost + "],Port[" + rePort + "],User[" + reUser
				+ "]");
		logger.debug(sb.toString());

		return in;
	}

	public String getStreamType(DataStore dataStore) {
		InputStream in = dataStore.getIn();
		// ,@Value("aa") String encoding
		String encoding = "";
		// 获取配置文件 以什么编码解析 默认UTF-8
		if (encoding == null || encoding.equals("")) {
			// 默认以utf-8形式
			encoding = "utf-8";
		}
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(in, encoding));
			String streamStr ;
			if ((streamStr=reader.readLine()) != null) {
				switch (streamStr.substring(0, 1)) {
				case "<":
					return "XML";
				case "{":
					return "JSON";
				default:
					return null;

				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}

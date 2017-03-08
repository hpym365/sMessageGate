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
import com.senyint.util.CloneUtils;

@Component("RECIVESTREAM")
public class ReciveStreamHandler extends BaseHandler implements Handler {

	Logger logger = Logger.getLogger(this.getClass());

	public void execute(DataStore dataStore) {
		Config config = this.getConfig(dataStore);
		String streamStr = "";
		try {
			streamStr = this.reciveStream(dataStore);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataStore.setStreamStr(streamStr);

		String streamType = this.getStreamType(dataStore);
		if (streamType == null)
			throw new RuntimeException("流文件类型检查失败  只支持 xml json");

		dataStore.setStreamType(streamType);
		System.out.println("ReciveStreamHandler execute");
	}

	public String reciveStream(DataStore dataStore) throws IOException {
		HttpServletRequest request = dataStore.getRequest();
		InputStream in = request.getInputStream();

		BufferedReader reader;
		String streamStr = "";
		try {
			reader = new BufferedReader(new InputStreamReader(in, dataStore.getEncoding()));
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				streamStr = streamStr + temp;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String reAddr = request.getRemoteAddr();
		String reHost = request.getRemoteHost();
		int rePort = request.getRemotePort();

		StringBuffer sb = new StringBuffer();
		sb.append("Command request from [" + reAddr + "],Host[" + reHost + "],Port[" + rePort + "]");
		logger.debug(sb.toString());

		return streamStr;
	}

	public String getStreamType(DataStore dataStore) {
		switch (dataStore.getStreamStr().substring(0, 1)) {
		case "<":
			return "XML";
		case "{":
			return "JSON";
		default:
			return "";
		}
	}
}

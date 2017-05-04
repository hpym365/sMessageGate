package com.senyint.handler.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.handler.BaseHandler;
import com.senyint.handler.Handler;
import com.senyint.log.SenyintLog;

/**
 * 
 * @ClassName: ReciveStreamHandler
 * @Description: 流接收Handler 接收流转换为String保存到dataStore的StrreamStr
 * @author hpym365@gmail.com
 * @date 2017年3月19日 下午9:42:30
 * @version V1.0
 */
@Component("RECIVESTREAM")
public class ReciveStreamHandler extends BaseHandler implements Handler {

	Logger logger = Logger.getLogger(this.getClass());

	public void execute(DataStore dataStore) {
		Config config = this.getConfig(dataStore);
		Object input = dataStore.getInput();
		String streamStr = "";

		if (input instanceof String) {
			streamStr = (String) input;
		} else {
			try {
				streamStr = this.reciveStream((InputStream) input, config.getEncode());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				SenyintLog.error(e);
			}
		}

		dataStore.setStreamStr(streamStr);

		String streamType = "";

		streamType = this.getStreamType(dataStore);

		System.out.println("继续执行了");
		if (streamType == null)
			throw new RuntimeException("流文件类型检查失败  只支持 xml json");

		dataStore.setStreamType(streamType);
		System.out.println("ReciveStreamHandler execute");
		// return streamType;
	}

	public String reciveStream(InputStream input, String encode) throws IOException {
		// HttpServletRequest request = dataStore.getRequest();

		BufferedReader reader;
		String streamStr = "";
		try {
			reader = new BufferedReader(new InputStreamReader(input, encode));
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				streamStr = streamStr + temp;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			SenyintLog.error(e);
		}

		return streamStr;
	}

	public String getStreamType(DataStore dataStore) {
		if (StringUtils.isNotEmpty(dataStore.getStreamStr())) {
			switch (dataStore.getStreamStr().substring(0, 1)) {
			case "<":
				return "XML";
			case "{":
				return "JSON";
			default:
				return "";
			}
		}
		return "";
	}
}

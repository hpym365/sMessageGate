package com.senyint.handler.impl;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import com.senyint.entity.DataStore;
import com.senyint.handler.BaseHandler;
import com.senyint.handler.Handler;
import com.senyint.log.SenyintLog;
import com.senyint.util.MapUtil;

/**
 * 
 * @ClassName: DataConvertMapHandler
 * @Description: 数据转换Handler 根据DataStore内的StreamString转Map
 * @author hpym365@gmail.com
 * @date 2017年3月19日 下午9:37:09
 * @version V1.0
 */
@Component("DATACONVERTMAP")
public class DataConvertMapHandler extends BaseHandler implements Handler {

	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void execute(DataStore dataStore) {
		// Config config = this.getConfig(dataStore);// 当前handler的配置
		// TODO Auto-generated method stub
		try {
			this.convertXmlToMap(dataStore, dataStore.getDataTag());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			SenyintLog.error(e);
		}

	}

	public void convertXmlToMap(DataStore dataStore, String dataTag) throws DocumentException {
		Document doc = new SAXReader().read(new ByteArrayInputStream(dataStore.getStreamStr().getBytes()));
		Map<String, Object> orginMap = MapUtil.Dom2Map(doc.getRootElement());
		Map<String, Object> orginData = dataStore.getOrginData();
		if (dataTag != null) {
			orginData.put(this.getConfig(dataStore).getSaveOrginDataKey(), orginMap.get(dataTag));
		} else {
			orginData.put(this.getConfig(dataStore).getSaveOrginDataKey(), orginMap);
		}
		dataStore.setOrginData(orginData);
		System.out.println(orginMap);
		// config 加入到datastore 然后根据读出来的流 判断什么开始的 如果是{ json 如果是<xml 如果是？？。。。。
	}

}

package com.senyint.ctrl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senyint.entity.DataStore;
import com.senyint.exception.CustomExcpetion;
import com.senyint.log.SenyintLog;
import com.senyint.serv.Serv;
import com.senyint.serv.ServiceFactory;
import com.senyint.util.YamlUtils;

/**
 * @author hpym365
 * @version 创建时间：2017年2月28日 上午1:31:22 类说明
 */
@RestController
@RequestMapping("/query")
public class QueryController {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	ServiceFactory servFactory;

	@RequestMapping("{command}")
	public Object init(ModelMap map, HttpServletRequest request, HttpServletResponse response,
			@PathVariable("command") String requestCommand) throws IOException {
		// String method = "qrsqd";// 流里获取到的传入执行哪个service
		// 测试 地址http://localhost:8080/query/qxsqd or qrsqd
		// 见配置application.properties
		// Map yaml = YamlUtil.yaml2Map("application.yaml");
		// Properties yaml2Properties =
		// YamlUtil.yaml2Properties("application.yaml");

		// ByteArrayInputStream in = this.reciveStream(request);

		DataStore dataStore = new DataStore();
		dataStore.setInput(request.getInputStream());
		dataStore.setRequestCommand(requestCommand);
		Map<String, Object> yaml = null;
		try {
			yaml = YamlUtils.getYaml();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			SenyintLog.error(e);
		}
		dataStore.setYaml(yaml);

		Serv serv = servFactory.getServ(dataStore);
		// map.put("serv",serv.toString());
		// map.put("servFactory",servFactory.toString());
		// System.out.println(this);
		try {
			serv.init(dataStore);
		} catch (CustomExcpetion e) {
			Map<String, String> exception = e.getException();
			map.addAllAttributes(exception);
			return exception;
		}

		return dataStore.getOutResult();
	}

}

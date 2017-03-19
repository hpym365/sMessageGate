package com.senyint.ctrl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senyint.component.TemplateConvert;
import com.senyint.serv.ServiceFactory;

/**
 * @author hpym365
 * @version 创建时间：2017年2月28日 上午1:31:22 类说明
 */
@RestController
@RequestMapping("/test")
public class TestController {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	ServiceFactory servFactory;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/")
	public Object init() {

		Map data = new HashMap();

		Map obj = new HashMap();

		obj.put("value1", 1);
		obj.put("value2", 2);

		data.put("name", "Hello world");
		data.put("age", "999");
		data.put("obj", obj);

		List stus = new ArrayList();

		Map stu1 = new HashMap();
		stu1.put("name", "xiao wang");
		Map stu2 = new HashMap();
		stu2.put("name", "xiao han");
		Map stu3 = new HashMap();
		stu3.put("name", "xiao lu");

		stus.add(stu1);
		stus.add(stu2);
		stus.add(stu3);

		data.put("stus", stus);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
		Calendar cal = Calendar.getInstance();

		// WebContext ctx = new WebContext(request, servletContext,
		// request.getLocale());
		data.put("today", dateFormat.format(cal.getTime()));

		data.put("today", Calendar.getInstance());
		TemplateConvert.convertMapByTemplate("person.xml", data);
		// String method = "qrsqd";// 流里获取到的传入执行哪个service
		// 测试 地址http://localhost:8080/query/qxsqd or qrsqd
		// 见配置application.properties

		return TemplateConvert.convertMapByTemplate("person.xml", data);
	}

}

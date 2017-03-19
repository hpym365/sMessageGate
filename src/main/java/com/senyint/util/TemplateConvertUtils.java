package com.senyint.util;

import java.util.Locale;
import java.util.Map;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

public class TemplateConvertUtils {

	// @SuppressWarnings("unused")
	// public static void main(String[] args) {
	//
	// String str = SystemConstants.XML_JSON_TEMPLATE_SRC;
	// Map data = new HashMap();
	//
	// Map obj = new HashMap();
	//
	// obj.put("value1", 1);
	// obj.put("value2", 2);
	//
	// data.put("name", "Hello world");
	// data.put("age", "999");
	// data.put("obj", obj);
	//
	// List stus = new ArrayList();
	//
	// Map stu1 = new HashMap();
	// stu1.put("name", "xiao wang");
	// Map stu2 = new HashMap();
	// stu2.put("name", "xiao han");
	// Map stu3 = new HashMap();
	// stu3.put("name", "xiao lu");
	//
	// stus.add(stu1);
	// stus.add(stu2);
	// stus.add(stu3);
	//
	// data.put("stus", stus);
	//
	// SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
	// Calendar cal = Calendar.getInstance();
	//
	// // WebContext ctx = new WebContext(request, servletContext,
	// // request.getLocale());
	// data.put("today", dateFormat.format(cal.getTime()));
	//
	// data.put("today", Calendar.getInstance());
	// String s =
	// TemplateConvertUtils.convertMapByTemplate("src/main/resources/template/",
	// "person.xml", data);
	// System.out.println(s);
	// }

	public static String convertMapByTemplate(String xmlJosnTemlateSrc, String templateFileName,
			Map<String, Object> data) {
		Locale locale = Locale.getDefault();

		FileTemplateResolver templateResolver = new FileTemplateResolver();
		// templateResolver.setTemplateMode("XML");
		// templateResolver.setPrefix("C:\\Users\\ludz\\Desktop\\pdfs");
		templateResolver.setPrefix(xmlJosnTemlateSrc);
		// templateResolver.setSuffix(".xml");
		templateResolver.setCacheTTLMs(3600000L);
		// templateResolver.setCharacterEncoding("utf-8");

		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);

		return templateEngine.process(templateFileName, new Context(locale, data));

	}
}

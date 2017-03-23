package com.senyint.test.test;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

public class Test {

	public static void main(String[] args) {
		convertMapByTemplate("src/main/resources/template/", "person.xml", new HashMap(), "utf-8");
	}

	public static String convertMapByTemplate(String xmlJosnTemlateSrc, String templateFileName,
			Map<String, Object> data, String charset) {
		Locale locale = Locale.getDefault();
		if (charset == null) {
			locale = Locale.getDefault();
		}
		FileTemplateResolver templateResolver = new FileTemplateResolver();
		// templateResolver.setTemplateMode("XML");
		// templateResolver.setPrefix(xmlJosnTemlateSrc);
		// templateResolver.setSuffix(".xml");
		templateResolver.setCacheTTLMs(3600000L);
		templateResolver.setCharacterEncoding("utf-8");

		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);

		return templateEngine.process(xmlJosnTemlateSrc + templateFileName, new Context(locale, data));

	}
}

package com.senyint.util;

import java.util.Locale;
import java.util.Map;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;
/**
 * 
 * @ClassName: TemplateConvertUtils
 * @Description: ThymeLeaf通过模板转换对象(根据模板和map生成)
 * @author hpym365@gmail.com
 * @date 2017年3月22日 下午10:44:02
 * @version V1.0
 */
public class TemplateConvertUtils {

	public static String convertMapByTemplate(String xmlJosnTemlateSrc, String templateFileName,
			Map<String, Object> data) {
		Locale locale = Locale.getDefault();

		FileTemplateResolver templateResolver = new FileTemplateResolver();
		// templateResolver.setTemplateMode("XML");
		templateResolver.setPrefix(xmlJosnTemlateSrc);
		// templateResolver.setSuffix(".xml");
		templateResolver.setCacheTTLMs(3600000L);
		// templateResolver.setCharacterEncoding("utf-8");

		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);

		return templateEngine.process(templateFileName, new Context(locale, data));

	}
}

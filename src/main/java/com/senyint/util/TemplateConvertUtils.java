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

	private static final String DefaultTemplateCharset = "GBK";
	private static final String DefaultCharset = "UTF-8";

	public static String convertMapByTemplate(String xmlJosnTemlateSrc, String templateFileName,
			Map<String, Object> data, String charset) {
		Locale locale = Locale.getDefault();
		if (charset == null) {
			locale = Locale.getDefault();
		}
		FileTemplateResolver templateResolver = new FileTemplateResolver();
		// templateResolver.setTemplateMode("XML");
		templateResolver.setPrefix(xmlJosnTemlateSrc);
		// templateResolver.setSuffix(".xml");
		templateResolver.setCacheTTLMs(3600000L);
		templateResolver.setCharacterEncoding(DefaultTemplateCharset);

		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);

		return templateEngine.process(templateFileName, new Context(locale, data));

	}

	public static String convertMapByTemplate(String xmlJosnTemlateSrc, String templateFileName,
			Map<String, Object> data) {
		return convertMapByTemplate( templateFileName, data, DefaultCharset);
	}

	
	public static String convertMapByTemplate(String templateFileName, Map<String, Object> data, String charset) {
		Locale locale = Locale.getDefault();
		if (charset == null) {
			locale = Locale.getDefault();
		}
		FileTemplateResolver templateResolver = new FileTemplateResolver();
		// templateResolver.setTemplateMode("XML");
		// templateResolver.setPrefix(xmlJosnTemlateSrc);
		// templateResolver.setSuffix(".xml");
		templateResolver.setCacheTTLMs(3600000L);
		templateResolver.setCharacterEncoding(DefaultTemplateCharset);

		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);

		return templateEngine.process(templateFileName, new Context(locale, data));

	}

	public static String convertMapByTemplate(String prefix, String suffix, Long cacheTTLMs, String templateCharset,
			String templateFileName, Map<String, Object> data, String charset, String templateMode) {
		Locale locale = Locale.getDefault();
		if (charset == null) {
			locale = Locale.getDefault();
		}
		FileTemplateResolver templateResolver = new FileTemplateResolver();
		templateResolver.setTemplateMode(templateMode);
		templateResolver.setPrefix(prefix);
		templateResolver.setSuffix(suffix);
		templateResolver.setCacheTTLMs(cacheTTLMs);
		templateResolver.setCharacterEncoding(templateCharset);

		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);

		return templateEngine.process(templateFileName, new Context(locale, data));

	}
}

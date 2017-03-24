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

	public static String convertMapByTemplate(String templatePath, String templateFileName, Map<String, Object> data,
			String charset) {
		FileTemplateResolver templateResolver = new FileTemplateResolver();
		templateResolver.setPrefix(templatePath);
		templateResolver.setCacheTTLMs(3600000L);
		templateResolver.setCharacterEncoding(DefaultTemplateCharset);
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		return templateEngine.process(templateFileName, new Context(Locale.getDefault(), data));
	}

	public static String convertMapByTemplate(String templatePathAndFileName, Map<String, Object> data) {
		FileTemplateResolver templateResolver = new FileTemplateResolver();
		templateResolver.setCacheTTLMs(3600000L);
		templateResolver.setCharacterEncoding(DefaultTemplateCharset);
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		return templateEngine.process(templatePathAndFileName, new Context(Locale.getDefault(), data));
	}

	public static String convertMapByTemplate(String templatePathAndFileName, Map<String, Object> data, Locale locale) {
		if (locale == null)
			locale = Locale.getDefault();
		FileTemplateResolver templateResolver = new FileTemplateResolver();
		templateResolver.setCacheTTLMs(3600000L);
		templateResolver.setCharacterEncoding(DefaultTemplateCharset);
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		return templateEngine.process(templatePathAndFileName, new Context(locale, data));
	}

	public static String convertMapByTemplate(String prefix, String suffix, Long cacheTTLMs, String templateCharset,
			String templateFileName, Map<String, Object> data, String charset, String templateMode) {
		FileTemplateResolver templateResolver = new FileTemplateResolver();
		templateResolver.setTemplateMode(templateMode);
		templateResolver.setPrefix(prefix);
		templateResolver.setSuffix(suffix);
		templateResolver.setCacheTTLMs(cacheTTLMs);
		templateResolver.setCharacterEncoding(templateCharset);
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		return templateEngine.process(templateFileName, new Context(Locale.getDefault(), data));
	}

}

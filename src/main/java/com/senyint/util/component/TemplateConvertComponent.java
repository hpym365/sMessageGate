package com.senyint.util.component;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.senyint.util.TemplateConvertUtils;
/**
 * 
 * @author hanpeng
 * 不应该在静态工具类直接读取环境变量
 * 使用component的类调用工具类 传入读取的配置文件变量
 */
@Component
public class TemplateConvertComponent {

	// @Value("${src}")
	// private String xmlJosnTemlateSrc;
	//
	// private static String XML_JSON_TEMPLATE_SRC;
	//
	// @PostConstruct
	// private void initialize() {
	// this.XML_JSON_TEMPLATE_SRC = this.xmlJosnTemlateSrc;
	// }

	private static String XML_JSON_TEMPLATE_SRC;

	@Value("${xmlandjson.template.src}")
	private void setXmlJosnTemlateSrc(String src) {
		XML_JSON_TEMPLATE_SRC = src;
	}

	public static String convertMapByTemplate(String templateFileName, Map<String, Object> data) {
		return TemplateConvertUtils.convertMapByTemplate(XML_JSON_TEMPLATE_SRC, templateFileName, data);
	}
}

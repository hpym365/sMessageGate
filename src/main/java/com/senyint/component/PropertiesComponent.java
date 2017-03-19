package com.senyint.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PropertiesComponent {

	@Autowired
	Environment env;

	private static PropertiesComponent propertiesUtils;

	@PostConstruct
	public void initialize() {
		propertiesUtils = this;
		propertiesUtils.env = this.env;
	}

	public static String getProperties(String key) {
		return propertiesUtils.env.getProperty(key) == null ? "" : propertiesUtils.env.getProperty(key).trim();
	}

	public static String getProperties(String key, String defaultValue) {
		return propertiesUtils.env.getProperty(key) == null ? defaultValue
				: propertiesUtils.env.getProperty(key).trim();
	}

	// public static String[] getAllProperties(){
	// return propertiesUtils.env.getActiveProfiles();
	// }

}

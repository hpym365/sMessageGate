package com.senyint.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtils {

	private static Properties properties;

	private static synchronized Properties getProInstance() throws Exception {
		if (properties == null) {
			Properties p = new Properties();
			p.load(new FileInputStream(new File("src/main/resources/application.properties")));
			return p;
		}
		return properties;
	}

	public static String getProperties(String key) throws Exception {
		properties = getProInstance();
		return properties.getProperty(key) == null ? "" : properties.getProperty(key).trim();
	}

	public static String getProperties(String key, String defaultValue) throws Exception {
		properties = getProInstance();
		return properties.getProperty(key) == null ? defaultValue : properties.getProperty(key).trim();
	}

}

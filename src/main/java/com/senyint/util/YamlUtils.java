package com.senyint.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YamlUtils {

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getYaml() throws Exception {
		String yamlPath = PropertiesUtils.getProperties("config.filepath", "src/main/resources/application.yaml");
		InputStream in = new FileInputStream(yamlPath);
		Yaml yaml = new Yaml();
		return yaml.loadAs(in, Map.class);
	}

	public static void setYaml(Map<String, Object> data) throws Exception {
		String yamlPath = PropertiesUtils.getProperties("config.filepath", "src/main/resources/application.yaml");
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(yamlPath), PropertiesUtils.getProperties("config.encode", "UTF-8"));
		new Yaml().dump(data, osw);
	}
}

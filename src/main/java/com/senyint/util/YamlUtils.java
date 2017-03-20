package com.senyint.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YamlUtils {

	private static Map<String, Object> yaml;

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getYaml() throws Exception {
		if (yaml == null) {
			String yamlPath = PropertiesUtils.getProperties("config.filepath", "src/main/resources/application.yaml");
			InputStream in = new FileInputStream(yamlPath);
			Yaml yaml = new Yaml();
			return yaml.loadAs(in, Map.class);
		}

		return yaml;
	}

	public static void setYaml(Map<String, Object> data) throws Exception {
		String yamlPath = PropertiesUtils.getProperties("config.filepath", "src/main/resources/application.yaml");
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(yamlPath),
				PropertiesUtils.getProperties("config.encode", "UTF-8"));
		new Yaml().dump(data, osw);
	}

	@SuppressWarnings("rawtypes")
	public static Object getYamlValue(String key) throws Exception {
		yaml = getYaml();
		String[] pre = key.split("\\.");
		// System.out.println(key);
		Object temp = yaml;
		for (int i = 0; i < pre.length; i++) {
			temp = ((Map) temp).get(pre[i]);
			if (temp == null)
				return null;
		}
		return temp;
	}
}

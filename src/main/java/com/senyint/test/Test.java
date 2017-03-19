package com.senyint.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.senyint.util.YamlUtils;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> yaml = null;
		yaml = YamlUtils.getYaml();
		System.out.println(yaml);
		yaml.put("enva", "wakaka");
		YamlUtils.setYaml(yaml);
	}

	public void test() {
		InputStream in = null;
		try {
			in = new FileInputStream("src/main/resources/application.yaml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Yaml yaml = new Yaml();
		// Map<String,Object> map = yaml.loadAs(in, Map.class);
		// Object object = map.get("haha");
		// Map object2 = (Map) map.get("zscs");
		// Object object3 = object2.get("handlerlist");
		// System.out.println(map);

		OutputStream out = null;
		try {
			out = new FileOutputStream("src/main/resources/a.yaml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OutputStreamWriter o = null;
		try {
			o = new OutputStreamWriter(out, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object obj = yaml.load(in);
		yaml.dump(obj, o);
	}

}

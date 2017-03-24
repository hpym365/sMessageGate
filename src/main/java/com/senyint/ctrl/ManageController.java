package com.senyint.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.senyint.util.YamlUtils;

@RestController
public class ManageController {

//	@RequestMapping(value = "/services", method = RequestMethod.GET)
//	public List getServices() throws Exception {
//
//		Object yamlValue = null;
//		try {
//			yamlValue = YamlUtils.getYamlValue("task");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Map<String, Object> map = (Map<String, Object>) yamlValue;
//		// map
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		for (String key : map.keySet()) {
//			Object object = map.get(key);
//			Map<String, Object> serv = new HashMap<String, Object>();
//			serv.put("serv", key);
//			serv.put("config", object);
//			list.add(serv);
//			System.out.println(key);
//		}
//		return list;
//	}

//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@RequestMapping(value = "/services", method = RequestMethod.POST)
//	public void updateServices(@RequestBody Map<String,Object> data) throws Exception {
//		Map map = (Map) data.get("data");
//		
//		Object yamlValue = null;
//		try {
//			yamlValue = YamlUtils.getYaml();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Map<String, Object> orgin = (Map<String, Object>) yamlValue;
//		Map task = (Map) orgin.get("task");
//		task.put((String)map.get("task"), map);
//		orgin.put("task",task);
//		YamlUtils.setYaml(orgin);
//		System.out.println("123");
//	}
//
//	@RequestMapping(value = "/services", method = RequestMethod.OPTIONS)
//	public void updateServicesa() {
//		System.out.println("456");
//	}
//
//	@SuppressWarnings({ "unchecked", "unused" })
//	public static void main(String[] args) {
//		Object yamlValue = null;
//		try {
//			yamlValue = YamlUtils.getYamlValue("task");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//		Map<String, Object> map = (Map<String, Object>) yamlValue;
//		// map
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		for (String key : map.keySet()) {
//			Object object = map.get(key);
//			Map<String, Object> serv = new HashMap<String, Object>();
//			serv.put("serv", key);
//			list.add(serv);
//			System.out.println(key);
//		}
//		System.out.println(yamlValue);
//	}
}

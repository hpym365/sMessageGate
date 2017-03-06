package com.senyint.util;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> parseJSON2Map(String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<JSONObject> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = it.next();
					list.add(parseJSON2Map(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}
	
	public static String map2Json(Map<String,Object> map) throws JsonGenerationException, JsonMappingException, IOException{
		return jsonFromObject(map);
	}
	
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object> json2Map(String json) throws JsonParseException, JsonMappingException, IOException{
		return jsonToObject(json, Map.class);
	}
	
	public static String jsonFromObject(Object object) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		config(om);
		return om.writeValueAsString(object);
	}

	public static <T> T jsonToObject(String json, Class<T> c) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		configReturnList(c, om);
		config(om);
		return om.readValue(json, c);
	}

	public static String jsonFromObjectWithDateFormat(Object object, String formatString) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		om.setDateFormat(new SimpleDateFormat(formatString));
		config(om);
		return om.writeValueAsString(object);
	}

	public static <T> T jsonToObjectWithDateFormat(String json, Class<T> c, String formatString) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		configReturnList(c, om);
		config(om);
		om.setDateFormat(new SimpleDateFormat(formatString));
		return om.readValue(json, c);
	}

	private static void config(ObjectMapper om) {
		// om.configure(Feature.AUTO_DETECT_FIELDS, true);
		// om.setPropertyNamingStrategy(new PropertyNamingStrategyDefultCase());
	}

	private static <T> void configReturnList(Class<T> c, ObjectMapper om) {
		Class<?>[] interfaces = c.getInterfaces();
		boolean b = false;
		for (Class<?> cl : interfaces) {
			b = List.class == cl;
			if (b) {
				break;
			}
		}
		if (b) {
			// 允许对象强转list
			om.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		}
	}
}

package com.senyint.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MapUtil {
	@SuppressWarnings("rawtypes")
	public static Map xml2map(String xmlString) throws DocumentException {
		Document doc = DocumentHelper.parseText(xmlString);
		Element rootElement = doc.getRootElement();
		Map<String, Object> map = new HashMap<String, Object>();
		ele2map(map, rootElement);
		// System.out.println(map);
		// 到此xml2map完成，下面的代码是将map转成了json用来观察我们的xml2map转换的是否ok
		// JSONObject j = JSONObject.fromObject(map);
		String string = JSONObject.fromObject(map).toString();
		System.out.println(string); // 打印json
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	static void ele2map(Map map, Element ele) {
		System.out.println(ele);
		// 获得当前节点的子节点
		List<Element> elements = ele.elements();
		if (elements.size() == 0) {
			// 没有子节点说明当前节点是叶子节点，直接取值即可
			map.put(ele.getName(), ele.getText());
		} else if (elements.size() == 1) {
			// 只有一个子节点说明不用考虑list的情况，直接继续递归即可
			Map<String, Object> tempMap = new HashMap<String, Object>();
			ele2map(tempMap, elements.get(0));
			map.put(ele.getName(), tempMap);
		} else {
			// 多个子节点的话就得考虑list的情况了，比如多个子节点有节点名称相同的
			// 构造一个map用来去重
			Map<String, Object> tempMap = new HashMap<String, Object>();
			for (Element element : elements) {
				tempMap.put(element.getName(), null);
			}
			Set<String> keySet = tempMap.keySet();
			for (String string : keySet) {
				Namespace namespace = elements.get(0).getNamespace();
				List<Element> elements2 = ele.elements(new QName(string, namespace));
				// 如果同名的数目大于1则表示要构建list
				if (elements2.size() > 1) {
					List<Map> list = new ArrayList<Map>();
					for (Element element : elements2) {
						Map<String, Object> tempMap1 = new HashMap<String, Object>();
						ele2map(tempMap1, element);
						list.add(tempMap1);
					}
					map.put(string, list);
				} else {
					// 同名的数量不大于1则直接递归去
					Map<String, Object> tempMap1 = new HashMap<String, Object>();
					ele2map(tempMap1, elements2.get(0));
					map.put(string, tempMap1);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> json2Map(String jsonStr) {
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
					list.add(json2Map(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}

	@SuppressWarnings("rawtypes")
	public static Map<String, Object> xml2Map(String xmlstr) throws DocumentException {
		Document doc = DocumentHelper.parseText(xmlstr);
		Map<String, Object> map = new HashMap<String, Object>();
		if (doc == null)
			return map;
		Element root = doc.getRootElement();
		for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			List list = e.elements();
			if (list.size() > 0) {
				map.put(e.getName(), Dom2Map(e));
			} else
				map.put(e.getName(), e.getText());
		}
		return map;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, Object> Dom2Map(Element e) {
		Map map = new HashMap();
		List list = e.elements();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Element iter = (Element) list.get(i);
				List mapList = new ArrayList();

				if (iter.elements().size() > 0) {
					Map m = Dom2Map(iter);
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (!obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(m);
						}
						if (obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = (List) obj;
							mapList.add(m);
						}
						map.put(iter.getName(), mapList);
					} else
						map.put(iter.getName(), m);
				} else {
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (!obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(iter.getText());
						}
						if (obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = (List) obj;
							mapList.add(iter.getText());
						}
						map.put(iter.getName(), mapList);
					} else
						map.put(iter.getName(), iter.getText());
				}
			}
		} else
			map.put(e.getName(), e.getText());
		return map;
	}

	/**
	 * 根据xml消息体转化为Map
	 * 
	 * @param xml
	 * @param rootElement
	 * @return
	 * @throws DocumentException
	 * @author 蔡政滦 modify by 2015-6-5
	 */
	public static Map xmlBody2map(String xml, String rootElement) throws DocumentException {
		org.dom4j.Document doc = DocumentHelper.parseText(xml);
		Element body = (Element) doc.selectSingleNode("/" + rootElement);
		Map vo = __buildXmlBody2map(body);
		return vo;
	}

	private static Map __buildXmlBody2map(Element body) {
		Map vo = new HashMap();
		if (body != null) {
			List<Element> elements = body.elements();
			for (Element element : elements) {
				String key = element.getName();
				if (key != null) {
					String type = element.attributeValue("type", "java.lang.String");
					String text = element.getText().trim();
					Object value = null;
					if (java.lang.String.class.getCanonicalName().equals(type)) {
						value = text;
					} else if (java.lang.Character.class.getCanonicalName().equals(type)) {
						value = new java.lang.Character(text.charAt(0));
					} else if (java.lang.Boolean.class.getCanonicalName().equals(type)) {
						value = new java.lang.Boolean(text);
					} else if (java.lang.Short.class.getCanonicalName().equals(type)) {
						value = java.lang.Short.parseShort(text);
					} else if (java.lang.Integer.class.getCanonicalName().equals(type)) {
						value = java.lang.Integer.parseInt(text);
					} else if (java.lang.Long.class.getCanonicalName().equals(type)) {
						value = java.lang.Long.parseLong(text);
					} else if (java.lang.Float.class.getCanonicalName().equals(type)) {
						value = java.lang.Float.parseFloat(text);
					} else if (java.lang.Double.class.getCanonicalName().equals(type)) {
						value = java.lang.Double.parseDouble(text);
					} else if (java.math.BigInteger.class.getCanonicalName().equals(type)) {
						value = new java.math.BigInteger(text);
					} else if (java.math.BigDecimal.class.getCanonicalName().equals(type)) {
						value = new java.math.BigDecimal(text);
					} else if (java.util.Map.class.getCanonicalName().equals(type)) {
						value = __buildXmlBody2map(element);
					} else {
					}
					vo.put(key, value);
				}
			}
		}
		return vo;
	}

	/*
	 * ===============================华丽分割线
	 */

	// @SuppressWarnings("rawtypes")
	// public Map genMapByXmlTemplate(String tempname, Map frommap) throws
	// FileNotFoundException, DocumentException {
	// InputStream in = IOUtil.getInSreamByFile(tempname);
	//
	// String instr = IOUtil.getStringByIn(in, "GBK"); // 输入的
	// Map map = new HashMap();
	// map = xmlTemplate2Map(instr, frommap);
	//
	// return map;
	// }

	@SuppressWarnings("rawtypes")
	public Map<String, Object> xmlTemplate2Map(String in, Map frommap) throws DocumentException {
		Document doc = DocumentHelper.parseText(in);
		Map<String, Object> map = new HashMap<String, Object>();
		if (doc == null)
			return map;
		Element root = doc.getRootElement();
		for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			// System.out.println(e.getName());
			List list = e.elements();
			// System.out.println(e.content());
			if (list.size() > 0) {
				map.put(e.getName(), templateDom2Map(e, frommap));
				// System.out.println(e.getName() + ":::-dom" + e);
			} else {

				String key = e.getText();
				Object obj = new Object();
				List temp = new ArrayList();
				if (key.indexOf(":list-filed:") > 0) {// 如果是list则....
					String keys[] = key.split(":list-filed:");
					obj = getMapValueByKeys(frommap, keys[0].split("\\."));
					if (keys[1].equals("none")) {// 该list不需要映射 则直接保存就可以
						map.put(e.getName(), obj);
					} else {
						temp = mappedListNewKey((List) obj, keys[1]);
						map.put(e.getName(), temp);
					}

				} else {
					obj = getMapValueByKeys(frommap, key.split("\\."));
					map.put(e.getName(), obj);
				}

				// 这里的都是String
				// String key[] = e.getText().split("\\.");
				// Object obj = getMapValueByKeys(frommap, key);
				// map.put(e.getName(), obj);
				// System.out.println(e.getName() + "::obj:" + e.getText());
			}

		}
		return map;
	}

	@SuppressWarnings("rawtypes")
	public Object getMapValueByKeys(Map map, String t[]) {// 根据persons.person获取对应的值
		HashMap tmap = CloneUtils.clone((HashMap) map);
		// tmap.putAll(map);//克隆一个 要不总改变原来的
		Object tmp = tmap;
		for (int i = 0; i < t.length; i++) {
			// if (tmp instanceof Map) {
			// tmp = ((Map<String, Object>) tmp).get(t[i]);
			// }
			tmp = getValueByKey(tmp, t[i]);
		}
		System.out.println("返回的obj:" + tmp);
		return tmp;
	}

	// 根据key查找
	public Object getValueByKey(Object tmp, String t) {
		if (tmp instanceof Map) {
			tmp = ((Map<String, Object>) tmp).get(t);
		} else if (tmp instanceof List) {
			List tmplist = new ArrayList();
			List reslist = new ArrayList();
			tmplist = (List) tmp;
			for (int i = 0; i < tmplist.size(); i++) {
				tmp = tmplist.get(i);
				tmp = getValueByKey(tmp, t);
				reslist.add(tmp);
			}
			tmp = reslist;
		}
		return tmp;
	}

	// from map 原map 就是传入的
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map templateDom2Map(Element e, Map frommap) {
		Map map = new HashMap();
		List list = e.elements();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Element iter = (Element) list.get(i);
				List mapList = new ArrayList();

				if (iter.elements().size() > 0) {

					Map m = templateDom2Map(iter, frommap);
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (!obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(m);
						}
						if (obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = (List) obj;
							mapList.add(m);
						}
						map.put(iter.getName(), mapList);
					} else
						map.put(iter.getName(), m);
					System.out.println("iter.getName():" + iter.getName() + " value:" + m);
				} else {
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (!obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(iter.getText());
						}
						if (obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = (List) obj;
							mapList.add(iter.getText());
						}
						map.put(iter.getName(), mapList);
					} else {
						String key = iter.getText();
						if (key.indexOf(":list-filed:") > 0) {// 如果是list则....
							String keys[] = key.split(":list-filed:");
							// mapz.putAll(frommap);
							Map test = new HashMap();
							test.putAll(frommap);
							test.put("123", "aa");

							Object obj = getMapValueByKeys(test, keys[0].split("\\."));

							// List tlist = (List) obj;
							// tlist.add("asdfff");
							if (keys[1].equals("none")) {// 该list不需要映射 则直接保存就可以
								map.put(iter.getName(), obj);
							} else {
								List temp = mappedListNewKey((List) obj, keys[1]);
								map.put(iter.getName(), temp);
							}

						} else {
							Object obj = getMapValueByKeys(frommap, key.split("\\."));
							map.put(iter.getName(), obj);
						}

						// System.out.println(e.getName()+"::obj:"+e.getText());
						// map.put(iter.getName(), iter.getText());
						// System.out.println(e.getName()+":aaa:"+iter.getText());
					}
				}
			}
		} else
			map.put(e.getName(), e.getText());
		return map;
	}

	// 把list里的map的key值根据传入的新旧key做更改
	// files = name=xm,age=nl
	// list 要处理的list
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public static List mappedListNewKey(List list, String files) {
		String keys[] = files.split(",");
		Object obj = null;
		List resList = new ArrayList();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof Map) {
					Map map = (Map) list.get(i);
					for (int j = 0; j < keys.length; j++) {
						String key[] = keys[j].split("\\=");
						map.put(key[1], map.get(key[0]));
						map.remove(key[0]);
					}
					resList.add(map);
				} else {
					resList.add(list.get(i));
				}
			}
		}
		System.out.println("resList:" + resList);
		return resList;
	}
}

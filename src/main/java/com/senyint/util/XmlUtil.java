package com.senyint.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.dom4j.io.SAXReader;

public class XmlUtil {
	// xStream???

	@SuppressWarnings("rawtypes")
	public static String map2Xml(Map map, String encode, String root) {
		StringBuffer sb = new StringBuffer();
		sb.append("<" + root + ">");// <?xml version=\"1.0\"
									// encoding=\""+encode+"\"?>
		mapToXml(map, sb);
		sb.append("</" + root + ">");
		try {
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	private static void mapToXml(Map map, StringBuffer sb) {
		Set set = map.keySet();
		for (Iterator it = set.iterator(); it.hasNext();) {
			String key = (String) it.next();
			Object value = map.get(key);
			if (null == value)
				value = "";
			if (value instanceof List) {
				ArrayList list = (ArrayList) map.get(key);
				// sb.append("<" + key + ">");
				for (int i = 0; i < list.size(); i++) {
					sb.append("<" + key + ">");
					// Object listi = list.get(i);
					if (list.get(i) instanceof HashMap) {
						HashMap hm = (HashMap) list.get(i);
						// sb.append("<" + key + ">");
						mapToXml(hm, sb);
						// sb.append("</" + key + ">");
					} else {
						// sb.append("<" + key + ">" + list.get(i) + "</" + key
						// + ">");
						sb.append(list.get(i));
					}
					// else
					// if(listi.getClass().getName().equals("java.util.ArrayList")){
					// sb.append("<" + key + ">" + "??" + "</" + key + ">");}

					sb.append("</" + key + ">");
				}
				// sb.append("</" + key + ">");

			} else {
				if (value instanceof HashMap) {
					sb.append("<" + key + ">");
					mapToXml((HashMap) value, sb);
					sb.append("</" + key + ">");
				} else {
					sb.append("<" + key + ">" + value + "</" + key + ">");
				}

			}

		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, Object> xml2Map(String in) throws DocumentException {
		Document doc = DocumentHelper.parseText(in);
		Map<String, Object> map = new HashMap<String, Object>();
		if (doc == null)
			return map;
		Element root = doc.getRootElement();
		for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			// System.out.println(e.getName());
			List list = e.elements();// 下面的节点数量
			if (list.size() > 0) {
				map.put(e.getName(), Dom2Map(e));
			} else {
				// 判断当前层同名节点数量
				Object tmp = map.get(e.getName());
				if (tmp == null) {// 证明该节点在当前目录没有出现过
					map.put(e.getName(), e.getText());
				} else {// 如果出现过
					if (tmp instanceof List) {
						((List) tmp).add(e.getText());
					} else {
						List tmplist = new ArrayList();
						tmplist.add(tmp);
						tmplist.add(e.getText());
						tmp = tmplist;
					}
					map.put(e.getName(), tmp);
				}

				e.getText();
			}

		}
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map Dom2Map(Element e) {
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

	public Document getDocumentByInputStream(InputStream in) throws Exception {
//		ByteArrayOutputStream output = new ByteArrayOutputStream();
//
//		int temp = in.read();
//		while (temp != -1) {
//			output.write(temp);
//			temp = in.read();
//		}
//
//		ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());

		BufferedReader reader = new BufferedReader(new InputStreamReader(in, ""));
		return new SAXReader().read(reader);
	}

}
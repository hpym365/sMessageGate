package com.senyint.util;

import java.util.HashMap;
import java.util.Map;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;

/**
 * @author hpym365
 * @version 创建时间：2017年2月28日 下午9:06:55 类说明
 */
public class Grovvy {


	public static void testGroovy2() {
		try {
//			String[] roots = new  String[]{".\\src\\main\\java\\com\\senyint\\util\\"} ;//定义Groovy脚本引擎的根路径
			String[] roots = new  String[]{"grovvy\\"} ;//定义Groovy脚本引擎的根路径
            GroovyScriptEngine engine = new GroovyScriptEngine(roots);
            
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("java","1.8");
            map.put("tomcat","8.0");
            Binding binding = new Binding();
            binding.setVariable("language", "Groovy");
            binding.setVariable("map", map);
            binding.setVariable("mapa", "mapa");
            Object value = engine.run("hello.groovy", binding);
            System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		new Grovvy().testGroovy2();
	}
}

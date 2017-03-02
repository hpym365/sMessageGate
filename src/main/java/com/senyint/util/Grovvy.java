package com.senyint.util;

import java.util.HashMap;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;

/**
 * @author hpym365
 * @version 创建时间：2017年2月28日 下午9:06:55 类说明
 */
@RestController
public class Grovvy {

	@Autowired
	Environment env;

	public static void testGroovy2() {
		try {
			// String[] roots = new
			// String[]{".\\src\\main\\java\\com\\senyint\\util\\"}
			// ;//定义Groovy脚本引擎的根路径
			String[] roots = new String[] { "grovvy\\" };// 定义Groovy脚本引擎的根路径
			GroovyScriptEngine engine = new GroovyScriptEngine(roots);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("java", "1.8");
			map.put("tomcat", "8.0");
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

	@RequestMapping("/groovy")
	public static void testGroovy1() {
		try {
			ScriptEngineManager factory = new ScriptEngineManager();
			ScriptEngine engine1 = factory.getEngineByName("groovy");
			ScriptEngine engine = factory.getEngineByName("javascript");
			String HelloLanguage = "println \"Welcome to $language\"";
			// String hello = env.getProperty("hello");
			// System.out.println(hello);
			engine.eval(HelloLanguage);
			Invocable inv = (Invocable) engine;
			Object[] params = { new String("Groovy") };
			Object result = inv.invokeFunction("hello", params);
			assert result.equals("Hello Groovy");
			System.err.println(result);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	public static void testGroovy3() {
		try {
			ScriptEngineManager factory = new ScriptEngineManager();
			ScriptEngine engine = factory.getEngineByName("groovy");
			String HelloLanguage = "def hello(language) {return \"Hello $language\"}";
			engine.eval(HelloLanguage);
			Invocable inv = (Invocable) engine;
			Object[] params = { new String("Groovy") };
			Object result = inv.invokeFunction("hello", params);
			assert result.equals("Hello Groovy");
			System.err.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {

			ScriptEngineManager factory = new ScriptEngineManager();

			ScriptEngine engine = factory.getEngineByName("groovy");

			String HelloLanguage = "def hello(language) {return \"Hello $language\"}";

			engine.eval(HelloLanguage);

			Invocable inv = (Invocable) engine;

			Object[] params = { new String("Groovy") };

			Object result = inv.invokeFunction("hello", params);

			assert result.equals("Hello Groovy");
			
			System.out.println(result);

		} catch (Exception e) {

			// TODO Auto-generatedcatch block
			e.printStackTrace();
		}
	}
}

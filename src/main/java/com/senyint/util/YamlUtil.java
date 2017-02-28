package com.senyint.util;

import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

/** 
* @author  hpym365
* @version 创建时间：2017年2月28日 下午10:51:32 
* 类说明  Yaml读取工具类
*/
public class YamlUtil {

    public static Map<String, Object> yaml2Map(String yamlSource) {
        try {
            YamlMapFactoryBean yaml = new YamlMapFactoryBean();
            yaml.setResources(new ClassPathResource(yamlSource));
            return yaml.getObject();
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }

    public static Properties yaml2Properties(String yamlSource) {
        try {
            YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
            yaml.setResources(new ClassPathResource(yamlSource));
            return yaml.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
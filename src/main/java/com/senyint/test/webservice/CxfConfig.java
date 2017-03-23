package com.senyint.test.webservice;

import java.util.Collection;

import javax.xml.ws.Endpoint;

import org.apache.catalina.core.ApplicationContextFacade;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.apache.http.protocol.RequestContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.RequestContext;

@Configuration
public class CxfConfig {

	// @Bean
	// public ServletRegistrationBean dispatcherRegistration(DispatcherServlet
	// dispatcherServlet) {
	// ServletRegistrationBean registration = new
	// ServletRegistrationBean(dispatcherServlet);
	// registration.getUrlMappings().clear();
	// registration.addUrlMappings("*.do");
	// registration.addUrlMappings("*.json");
	// return registration;
	// }

	/**
	 * 默认的servlet DispathchServlet 注意这个方法的名字不能是dispatcher***特别注意
	 * 如果不增加这个只增加cfxServetlet 的webservice的映射那么默认的就给顶没了
	 * @Title: defaultServlet @author hpym365@gmail.com @Description:
	 * TODO @param @param dispatcherServlet @param @return 设定文件 @return
	 * ServletRegistrationBean 返回类型 @throws
	 */
	@Bean
	public ServletRegistrationBean defaultServlet(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet);
		servletRegistrationBean.addUrlMappings("/");
		return servletRegistrationBean;
	}

	//
	@Bean
	public ServletRegistrationBean cfxServetlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new CXFServlet());
		servletRegistrationBean.addUrlMappings("/soap/*");
		return servletRegistrationBean;
	}

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}

	@Bean
	public IMyService userService() {
		return new MyServiceImpl();
	}

	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), userService());
		endpoint.publish("/hello");
		return endpoint;
	}
}
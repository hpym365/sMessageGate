package com.senyint.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senyint.serv.BaseServ;
import com.senyint.serv.ServiceFactory;

/**
 * @author hpym365
 * @version 创建时间：2017年2月28日 上午1:31:22 类说明
 */
@RestController
@RequestMapping("/query")
public class QueryController {

	BaseServ serv;

	@Autowired
	ServiceFactory servFactory;

	@RequestMapping("{method}")
	public String init(@PathVariable("method") String method) {
//		String method = "qrsqd";// 流里获取到的传入执行哪个service
		//测试 地址http://localhost:8080/query/qxsqd or qrsqd 见配置application.properties
		serv = servFactory.getHandler(method);
		serv.init();
		System.out.println(serv);
		return "execute finished";
	}

	// @Autowired
	// @Qualifier(method)
	// public void setBase(BaseInterface base) {
	// this.base = base;
	// }
}

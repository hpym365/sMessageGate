package com.senyint.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping("")
	public String init() {
		String method = "qrsqd";// 流里获取到的传入执行哪个service
		serv = servFactory.getHandler(method);

		System.out.println(serv);
		return "init";
	}

	// @Autowired
	// @Qualifier(method)
	// public void setBase(BaseInterface base) {
	// this.base = base;
	// }
}

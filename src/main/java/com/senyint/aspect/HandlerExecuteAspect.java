package com.senyint.aspect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.senyint.entity.Config;
import com.senyint.entity.DataStore;
import com.senyint.handler.HandlerFactory;

@Aspect
@Configuration
public class HandlerExecuteAspect {

	@Autowired
	HandlerFactory handlerFactory;

	// @Pointcut("execution(* com.senyint.handler.*.execute*(java.util.Map)) &&
	// args(map)")
	@Pointcut("execution(* com.senyint.handler.*.execute*(com.senyint.entity.DataStore)) && args(dataStore)")
	private void handlerExecutePointcut(DataStore dataStore) {
	}

	/**
	 * 集中处理handler执行前调用 aspect切
	 * 
	 * @param map
	 * @return
	 */
	@Around(value = "handlerExecutePointcut(dataStore)")
	public void beforeHandlerExecuteDo(ProceedingJoinPoint joinpoint, DataStore dataStore) throws Throwable {
		Config config = (Config) dataStore.get(joinpoint.getTarget().toString());
		executeDep(config, dataStore);
		List<Config> navList = dataStore.getHandlerExecuteNav() == null ? new ArrayList<Config>()
				: dataStore.getHandlerExecuteNav();
		navList.add(config);
		dataStore.setHandlerExecuteNav(navList);
		joinpoint.proceed();// go
	}

	public void executeDep(Config config, DataStore dataStore) {
		if (config.getDepList() != null) {
			// 执行依赖调用
			List<Map<String, Object>> depList = config.getDepList();
			List<Config> configList = handlerFactory.getHandlerListByList(depList);
			for (int i = 0; i < configList.size(); i++) {
				Config cfg = (Config) configList.get(i);
				dataStore.put(cfg.getHandler().toString(), cfg);
				cfg.getHandler().execute(dataStore);
			}
		}
	}
}

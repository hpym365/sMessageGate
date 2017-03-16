package com.senyint.log;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.senyint.exception.CustomExcpetion;

@Component
public class SenyintLog {

	Logger logger = Logger.getLogger(this.getClass());

	private static SenyintLog logs;

	@PostConstruct
	public void initialize() {
		logs = this;
		logs.logger = this.logger;
	}

	public static void error(Exception e, String errorCode, String errorMsg) {

		StackTraceElement[] stackTrace = e.getStackTrace();
		List<StackTraceElement> senyintList = new ArrayList<StackTraceElement>();

//		Thread.currentThread().getStackTrace();
		String nav = "";
		for (StackTraceElement iteam : stackTrace) {
			if (iteam.getClassName().startsWith("com.senyint") && iteam.getLineNumber() > 0) {
				senyintList.add(iteam);
				nav = nav + "->" + iteam.getClassName() + ":" + iteam.getMethodName() + ":" + iteam.getLineNumber();
			}
		}

		logs.logger.error("[ERROR]:错误位置:" + nav);
		logs.logger.error("[ERROR]:异常信息:" + e.getMessage());

		// throw new RuntimeException("测试异常抛出");
		throw new CustomExcpetion(errorCode, errorMsg);

		// try {
		//
		// } catch (Exception ex) {
		// // ex.printStackTrace();
		// // throw new RuntimeException("Logs出问题了 控制异常的东西坏了");
		// }
	}
	
	public static void debug(String message) {

		
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement stack = stackTrace[2];
//		stackTrace[2].
//		Class<? extends StackTraceElement[]> class1 = stackTrace.getClass();
//		Logger logger = Logger.getLogger(stackTrace[2].getClass());
		
//		String nav = "";
//		for (StackTraceElement iteam : stackTrace) {
//			if (iteam.getClassName().startsWith("com.senyint") && iteam.getLineNumber() > 0) {
//				senyintList.add(iteam);
//				nav = nav + "->" + iteam.getClassName() + ":" + iteam.getMethodName() + ":" + iteam.getLineNumber();
//				System.out.println(iteam.getClassName());
//			}
//		}
		
//		logger.debug("[DEBUG]:"+stack.getClassName() + ":" + stack.getMethodName() + ":" + stack.getLineNumber()+": "+message);
		logs.logger.debug("[DEBUG]:"+stack.getClassName() + ":" + stack.getMethodName() + ":" + stack.getLineNumber()+": "+message);

	}
}

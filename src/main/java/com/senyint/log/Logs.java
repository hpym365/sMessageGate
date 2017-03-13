package com.senyint.log;

import org.apache.log4j.Logger;

import com.senyint.exception.CustomExcpetion;

public class Logs {

	Logger logger = Logger.getLogger(getClass());
	
	public static void test(){
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
        
        System.out.println(className);
        System.out.println(methodName);
        System.out.println(lineNumber);

		try{
			throw new CustomExcpetion("测试异常抛出");
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("Logs出问题了   控制异常的东西坏了"); 
		}
	}
}

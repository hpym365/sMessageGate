package com.senyint.exception;

import java.util.HashMap;
import java.util.Map;

public class CustomExcpetion extends RuntimeException {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	private String errorCode;
	
	private String errorMes;
	
	public CustomExcpetion(String errorCode,String errorMes){
		super(errorMes);
		this.errorCode=errorCode;
		this.errorMes=errorMes;
	}
	
	public Map<String,String> getException(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("errorCode", errorCode);
		map.put("errorMes", errorMes);
		return map;
	}
}

package com.senyint.exception;

public class CustomExcpetion extends Exception {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	private String errorCode;
	
	private String errorMes;
	
	public CustomExcpetion(String errorCode){
		super(errorCode);
	}
}

package com.mindtree.shoppingkart.exception;

public class DataNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2395427258096302929L;
	
	public DataNotFoundException(String message){
		super(message);
	}

}

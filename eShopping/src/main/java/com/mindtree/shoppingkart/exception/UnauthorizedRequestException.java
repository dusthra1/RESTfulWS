package com.mindtree.shoppingkart.exception;

public class UnauthorizedRequestException extends RuntimeException{

	private static final long serialVersionUID = 4488264150647141744L;
	
	public UnauthorizedRequestException(String message){
		super(message);
	}

}

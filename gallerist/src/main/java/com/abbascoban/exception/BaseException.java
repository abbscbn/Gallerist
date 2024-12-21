package com.abbascoban.exception;

public class BaseException extends RuntimeException{
	
	// github denemesi
	
	public BaseException(ErrorMessage errorMessage) {
		super(errorMessage.prepareErrorMessage());
	}

}

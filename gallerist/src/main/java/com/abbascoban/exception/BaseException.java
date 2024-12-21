package com.abbascoban.exception;

public class BaseException extends RuntimeException{
	
	// tekrar
	
	public BaseException(ErrorMessage errorMessage) {
		super(errorMessage.prepareErrorMessage());
	}

}

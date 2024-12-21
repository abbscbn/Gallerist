package com.abbascoban.exception;

public class BaseException extends RuntimeException{
	
	// Temel hataların fırlatıldığı yer
	
	public BaseException(ErrorMessage errorMessage) {
		super(errorMessage.prepareErrorMessage());
	}

}

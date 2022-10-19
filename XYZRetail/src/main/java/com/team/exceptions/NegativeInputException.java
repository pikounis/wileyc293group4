package com.team.exceptions;

public class NegativeInputException extends Exception {

	String errorCode;
	
	public NegativeInputException(String errorCode) {
		this.setErrorCode(errorCode);
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}

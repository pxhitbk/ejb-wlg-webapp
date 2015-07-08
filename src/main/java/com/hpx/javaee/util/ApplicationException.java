package com.hpx.javaee.util;

public class ApplicationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2836162955270249623L;
	
	private final ApplicationErrorCode code;
	
	public ApplicationException(ApplicationErrorCode code, String message) {
		super(message);
		this.code = code;
	}

	public ApplicationErrorCode getCode() {
		return code;
	}
}

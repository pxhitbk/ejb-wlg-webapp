package com.hpx.javaee.util;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ServiceError {
	private String errorCode;
	private String message;

	public ServiceError(String errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

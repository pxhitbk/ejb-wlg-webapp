package com.hpx.javaee.util;

public enum ApplicationErrorCode {
	UNHANDED_ERROR("unhandle_error"), INVALID_NAME_TYPE("invalid_name_type"), USER_EXISTED("user_existed"), INVALID_INPUT("invalid_input");
	
	private String name;
	
	ApplicationErrorCode(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}

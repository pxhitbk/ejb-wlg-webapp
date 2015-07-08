package com.hpx.javaee.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

public @XmlRootElement
class LoginForm {
	private String username;
	private String password;
	
	public LoginForm() {
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

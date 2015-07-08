package com.hpx.javaee.entity;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

public class User {
	@FormParam("username") 
	@NotNull
	private String username;
	@FormParam("password")
	@NotNull
	private String password;
	public User() {}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		if (username == null) {
			throw new ValidationException();
		}
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

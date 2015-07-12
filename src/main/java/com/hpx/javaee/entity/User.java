package com.hpx.javaee.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

public class User {
	private Long id;
	private String username;
	private String password;
	private Set<UserProject> userProjects = new HashSet<UserProject>();
	
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<UserProject> getUserProjects() {
		return userProjects;
	}
	public void setUserProjects(Set<UserProject> userProjects) {
		this.userProjects = userProjects;
	}
}

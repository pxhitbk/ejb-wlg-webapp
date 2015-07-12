package com.hpx.javaee.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserProject {
	@Id
	private Long id;
	private User user;
	private Project project;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
}

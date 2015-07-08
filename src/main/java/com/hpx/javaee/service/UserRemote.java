package com.hpx.javaee.service;

import javax.ejb.Remote;

import com.hpx.javaee.entity.User;

@Remote
public interface UserRemote {
	public User create(String username, String password);
}

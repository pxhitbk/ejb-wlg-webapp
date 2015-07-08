package com.hpx.javaee.service;

import javax.ejb.Stateless;

import com.hpx.javaee.entity.User;
import com.hpx.javaee.util.ApplicationException;
import com.hpx.javaee.util.ApplicationErrorCode;

@Stateless(name="UserBean", mappedName="UserBean")
public class UserBean implements UserRemote {
	public User create(String username, String password) {
		if (username.equals("phamhung")) {
			throw new ApplicationException(ApplicationErrorCode.USER_EXISTED, "User already existed");
		}
		return new User(username, password);
	}
}

package com.hpx.javaee.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;

import com.hpx.javaee.entity.Project;
import com.hpx.javaee.entity.User;
import com.hpx.javaee.util.ApplicationException;
import com.hpx.javaee.util.ApplicationErrorCode;

@Stateless(name="UserBean", mappedName="UserBean")
public class UserBean implements UserRemote {
	@PersistenceContext
	private EntityManager entityManager;
	
	public User create(String username, String password) {
		List<User> user = entityManager
				.createQuery("SELECT u FROM " +  User.class.getName() + " u WHERE u.username LIKE :username", User.class)
				.setParameter("username", username)
				.getResultList();
		Project p = new Project();
		p.setName("PRJ-" + username);
		entityManager.persist(p);
		if (user != null && user.size() > 0) {
			throw new ApplicationException(ApplicationErrorCode.USER_EXISTED, "User already existed");
		}
		
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		entityManager.persist(newUser);
		return newUser;
	}
}

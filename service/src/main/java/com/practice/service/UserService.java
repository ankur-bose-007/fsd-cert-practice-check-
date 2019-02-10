package com.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.dao.UserDao;
import com.practice.entity.User;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public User signup(User user){
		if(user==null)
			return null;
		return userDao.signup(user);
	}
	public User getUserDetails(String email){
		if(email==null)
			return null;
		return userDao.findByUsername(email);
	}
}

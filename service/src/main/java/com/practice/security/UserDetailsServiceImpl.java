package com.practice.security;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practice.dao.UserDao;
import com.practice.entity.User;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	private UserDao userDao;
	
	public UserDetailsServiceImpl(UserDao userDao){
		this.userDao=userDao;
	}
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		logger.info("Getting details for->{}",email);
		User user=userDao.findByUsername(email);
		if (user == null) {
			logger.info("null object");
            throw new RuntimeException(email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList());
	}

}

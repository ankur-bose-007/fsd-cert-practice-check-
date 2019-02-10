package com.practice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.practice.dao.UserDao;
import com.practice.entity.User;
import com.practice.service.UserService;
import static org.mockito.Mockito.*;
public class ServiceTestClass{
	@Rule
	public MockitoRule rule=MockitoJUnit.rule();
	@InjectMocks
	UserService userService;
	
	User user;
	@Mock
	UserDao userDao;
	@Before
	public void initialize(){
		user=new User();
		user.setEmail("bose.jeet@gmail.com");
		user.setAge(19);
		user.setGender("male");
		user.setPassword("dsadadsa");
	}
	
	@Test
	public void successfulSignUpTest(){
		when(userDao.signup(user)).thenReturn(user);
		Assert.assertEquals(user,userService.signup(user));
		verify(userDao).signup(user);
	}
	@Test
	public void unsuccessfulSignUpTest() {
		Assert.assertEquals(null,userService.signup(null));
	}
	@Test
	public void successfullyGetUserDetails() {
		when(userDao.findByUsername(user.getEmail())).thenReturn(user);
		Assert.assertEquals(user, userService.getUserDetails(user.getEmail()));
		verify(userDao).findByUsername(user.getEmail());
	}
	@Test
	public void unsuccessfullyGetUserDetails() {
		when(userDao.findByUsername(user.getEmail())).thenReturn(null);
		Assert.assertEquals(null, userService.getUserDetails(user.getEmail()));
		verify(userDao).findByUsername(user.getEmail());
	}
	@Test
	public void nullGetUserDetails() {
		Assert.assertEquals(null,userService.getUserDetails(null));
	}
}

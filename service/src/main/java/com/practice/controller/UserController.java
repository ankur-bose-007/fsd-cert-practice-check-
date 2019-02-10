package com.practice.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.entity.User;
import com.practice.security.JWTAuthenticationFilter;
import com.practice.service.UserService;

@RestController
@RequestMapping("user")
public class UserController extends GlobalErrorHandlerController{
	Logger logger=LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("userDetails/{email}")
	public User getUserDetails(@PathVariable("email") String email){
		User user=null;
		user = userService.getUserDetails(email);
		return user; 
	}
	@PostMapping("login")
	public ResponseEntity<?> login() {
		return new ResponseEntity("User logged in",HttpStatus.OK);
	}
	@PostMapping("signup")
	public ResponseEntity<User> signup(@Valid @RequestBody User user){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user=userService.signup(user);
		if(user!=null)
			return new ResponseEntity<User>(user,HttpStatus.CREATED);
		
		return new ResponseEntity<User>(user,HttpStatus.CONFLICT);
	}
}

package com.practice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.entity.User;


//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTestClass {
	@Autowired
	private WebApplicationContext webApplicationContext;
	User user,user2,user3,user4,user5;
	ObjectMapper mapper;
	private MockMvc mockMvc;
	@Before
	public void initialize(){
		mockMvc=MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		//initializing non-existing user object
		user=new User();
		user.setAge(12);
		user.setEmail("jeet.ankur007@gmail.com");
		user.setGender("male");
		user.setPassword("Password@10");
		mapper=new ObjectMapper();
		
		//initializing existing user object
		user2=new User();
		user2.setAge(21);
		user2.setEmail("bose.ankur007@gmail.com");
		user2.setGender("male");
		user2.setPassword("Password@46");
		
		//initializing null user object
		user3=new User();
		user3.setAge(0);
		user3.setEmail("");
		user3.setGender("");
		user3.setPassword("");
		
		//initializing object with wrong email pattern
		user4=new User();
		user4.setAge(9);
		user4.setEmail("ankur@bose");
		user4.setGender("male");
		user4.setPassword("Password@46");
		
		//initializing object with wrong password pattern
		user5=new User();
		user5.setAge(9);
		user5.setEmail("jeet.ankur007@gmail.com");
		user5.setGender("male");
		user5.setPassword("Password");
	}
	@Test
	public void testIfCreated() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/signup")
		.content(mapper.writeValueAsString(user))
		.contentType("application/json;charset=UTF-8"))
		.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	@Test
	public void testIfConflict() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/signup")
		.content(mapper.writeValueAsString(user2))
		.contentType("application/json;charset=UTF-8"))
		.andExpect(MockMvcResultMatchers.status().isConflict());
	}
	@Test
	public void testIfNull() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/user/signup")
		.content(mapper.writeValueAsString(user3))
		.contentType("application/json;charset=UTF-8"))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	@Test
	public void testIfEmailPatternWrong() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/user/signup")
		.content(mapper.writeValueAsString(user4))
		.contentType("application/json;charset=UTF-8"))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	@Test
	public void testIfPasswordPatternWrong() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/user/signup")
		.content(mapper.writeValueAsString(user5))
		.contentType("application/json;charset=UTF-8"))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
}

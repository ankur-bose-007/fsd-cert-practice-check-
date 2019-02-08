package com.practice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.entity.User;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserControllerTestClass {
	@Autowired
	private WebApplicationContext webApplicationContext;
	String userObject;
	String userObject2;
	User user;
	ObjectMapper mapper;
	private MockMvc mockMvc;
	@Before
	public void initialize(){
		mockMvc=MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		user=new User();
		user.setAge(12);
		user.setEmail("saadsdsads");
		user.setGender("male");
		user.setPassword("dddasasd");
		mapper=new ObjectMapper();
		userObject="{\"email\":\"dass\"" + ","
				+ "\"password\":\"sdadasdas\"" + ","
				+ "\"gender\":\"male\"" + "}" + ","
				+ "\"age\":21" + "}";
		userObject2="{\"email\":\"bose.ankur007\"" + ","
				+ "\"password\":\"dsadadsa\"" + ","
				+ "\"gender\":\"male\"" + "}" + ","
				+ "\"age\":19" + "}";
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
		.content(userObject)
		.contentType("application/json;charset=UTF-8"))
		.andExpect(MockMvcResultMatchers.status().isConflict());
	}
}

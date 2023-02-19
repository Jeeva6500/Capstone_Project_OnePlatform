package com.natwest.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import com.natwest.model.UserProfile;
import com.natwest.service.UserService;

@RequestMapping("/api/user")
@WebMvcTest
public class UserControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	
	@MockBean
	UserService userServ;

	//convert the java object to json string format
	ObjectMapper mapper = new ObjectMapper();
	ObjectWriter writer = mapper.writer();
	
	List<UserProfile> userList = null;
	
	
	@BeforeEach
	void setUp() throws Exception {
		userList = new LinkedList<>();
		
		userList.add(new UserProfile("atnh524f","9633852741", "Jeeva", "jeeva123@gmail.com", "ATRPJ5264D", "Jeeva@123"));
		userList.add(new UserProfile("hjtg526d","9633852241", "Suresh", "suresh123@gmail.com", "BSRPJ5264D", "Suresh@123"));
		userList.add(new UserProfile("kluh527d","9633852341", "Priya", "priya123@gmail.com", "HTYPJ5264D", "Priya@123"));
	}

	@AfterEach
	void tearDown() throws Exception {
		userList = null;
	}
	
	@Test
	void testAddUser() throws Exception {
		UserProfile userProfile = new UserProfile("htyk879f","9633852541", "Prabha", "prabha123@gmail.com", "PARPJ5264D", "Prabha@123");
		try {
			when(userServ.addUser(userProfile)).thenReturn(userProfile);
			assertNotNull(userProfile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		String content = writer.writeValueAsString(userProfile);
		mockMvc.perform(post("/api/user/add").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
	}

	@Test
	void testGetUserList() throws Exception {
//		Using mockito to return the fake list instead of the real list
		when(userServ.userList()).thenReturn(userList);
		
		mockMvc.perform(get("/api/user/get").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	void testgetByUserName() throws Exception {
		when(userServ.getByUserName("jeeva123@gmail.com")).thenReturn(new UserProfile("jeeva123@gmail.com", "jeeva@123", "Jeeva", "9633852741", "Bangalore", "123456789123"));
		
		mockMvc.perform(get("/api/user/get/jeeva123@gmail.com").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		
	}
}

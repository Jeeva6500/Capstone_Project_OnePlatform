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
import com.natwest.model.Accounts;
import com.natwest.service.AccountsService;

@RequestMapping("/api/accounts")
@WebMvcTest
public class AccountsControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	
	@MockBean
	AccountsService accountsServ;

	//convert the java object to json string format
	ObjectMapper mapper = new ObjectMapper();
	ObjectWriter writer = mapper.writer();
	
	List<Accounts> accountsList = null;
	
	
	@BeforeEach
	void setUp() throws Exception {
		accountsList = new LinkedList<>();
		
		accountsList.add(new Accounts("1234567893", "savings", "HDFC", "HDFC0102031", "50000", null,"ghtyj25h"));
			
	}

	@AfterEach
	void tearDown() throws Exception {
		accountsList = null;
	}
	
	@Test
	void testAddAccounts() throws Exception {
		Accounts accounts = new Accounts("1234567892", "current", "ICICI", "ICIC0102031", "80000", null,"ghtyj25h");
		try {
			when(accountsServ.addAccount(accounts)).thenReturn(accounts);
			assertNotNull(accounts);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		String content = writer.writeValueAsString(accounts);
		mockMvc.perform(post("/api/accounts/add").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
	}

	@Test
	void testgetAllAccounts() throws Exception {
//		Using mockito to return the fake list instead of the real list
		when(accountsServ.getAllAcc()).thenReturn(accountsList);
		
		mockMvc.perform(get("/api/accounts/get").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	
//	@Test
//	void testGetAllAccountsByCustomerId() throws Exception {
//	when(accountsServ.getByCustomerId("ghtyj25h")).thenReturn(new Accounts("1234567893", "savings", "HDFC", "HDFC0102031", "50000", null,"ghtyj25h"));
//		mockMvc.perform(get("/api/accounts/getaccount/ghtyj25h").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
//	}
}



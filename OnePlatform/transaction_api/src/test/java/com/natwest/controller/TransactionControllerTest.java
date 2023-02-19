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
import com.natwest.model.Transaction;
import com.natwest.service.TransactionService;


@RequestMapping("/api/transaction")
@WebMvcTest
public class TransactionControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	
	@MockBean
	TransactionService transactionServ;

	//convert the java object to json string format
	ObjectMapper mapper = new ObjectMapper();
	ObjectWriter writer = mapper.writer();
	
	List<Transaction> transactionList = null;
	
	
	@BeforeEach
	void setUp() throws Exception {
		transactionList = new LinkedList<>();
		
		transactionList.add(new Transaction(101, "Debit", 10000, null,"Bill payment","123456789963"));
			
	}

	@AfterEach
	void tearDown() throws Exception {
		transactionList = null;
	}
	
	@Test
	void testAddTrans() throws Exception {
		Transaction transaction = new Transaction(102, "Debit", 500, null,"Medicone payment","123456789965");
		try {
			when(transactionServ.addTransaction(transaction)).thenReturn(true);
			assertNotNull(transaction);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		String content = writer.writeValueAsString(transaction);
		mockMvc.perform(post("/api/transaction/add").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
	}

	@Test
	void testFetchAll() throws Exception {
//		Using mockito to return the fake list instead of the real list
		when(transactionServ.fetchAllTransactions()).thenReturn(transactionList);
		
		mockMvc.perform(get("/api/transaction/get").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	
	//@Test
	//void testGetCardByAccountNumber() throws Exception {
	//when(cardServ.findByAccountNumber("123456789963")).thenReturn(new Card("1234567893", 20000, 10000, 10000, null, null,"123","123456789963"));
	//	mockMvc.perform(get("/api/accounts/getcard/123456789963").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	//}
}





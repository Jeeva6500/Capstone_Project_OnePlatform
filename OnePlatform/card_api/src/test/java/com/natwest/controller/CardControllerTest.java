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
import com.natwest.model.Card;
import com.natwest.service.CardService;


@RequestMapping("/api/card")
@WebMvcTest
public class CardControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	
	@MockBean
	CardService cardServ;

	//convert the java object to json string format
	ObjectMapper mapper = new ObjectMapper();
	ObjectWriter writer = mapper.writer();
	
	List<Card> cardList = null;
	
	
	@BeforeEach
	void setUp() throws Exception {
		cardList = new LinkedList<>();
		
		cardList.add(new Card("1234567893", 20000, 10000, 10000, null, null,"123","123456789963"));
			
	}

	@AfterEach
	void tearDown() throws Exception {
		cardList = null;
	}
	
	@Test
	void testSaveCard() throws Exception {
		Card card = new Card("1234567899", 30000, 10000, 20000, null, null,"222","123456789982");
		try {
			when(cardServ.addCard(card)).thenReturn(true);
			assertNotNull(card);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		String content = writer.writeValueAsString(card);
		mockMvc.perform(post("/api/card/add").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
	}

	@Test
	void testFetchAll() throws Exception {
//		Using mockito to return the fake list instead of the real list
		when(cardServ.fetchAllCards()).thenReturn(cardList);
		
		mockMvc.perform(get("/api/card/get").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	
	//@Test
	//void testGetCardByAccountNumber() throws Exception {
	//when(cardServ.findByAccountNumber("123456789963")).thenReturn(new Card("1234567893", 20000, 10000, 10000, null, null,"123","123456789963"));
	//	mockMvc.perform(get("/api/accounts/getcard/123456789963").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	//}
}





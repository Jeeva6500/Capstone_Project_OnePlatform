package com.natwest.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.natwest.exception.AccountNumberDoesNotExist;
import com.natwest.exception.CardNumberAlreadyExist;
import com.natwest.exception.CardNumberDoesNotExist;
import com.natwest.model.Card;
import com.natwest.service.CardService;

@RestController
@RequestMapping("/api/card")
public class CardController {
	
	@Autowired
	private CardService cardService;
	
	@PostMapping("/add")
public ResponseEntity<?> saveCard(@RequestBody Card card) {
		
		try {
			card.setUpdatedAt(LocalDateTime.now());
			cardService.addCard(card);
			return new ResponseEntity<Card>(card, HttpStatus.CREATED);
		} catch (CardNumberAlreadyExist e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/update/{id}")
public ResponseEntity<?> updateCard(@PathVariable int id, @RequestBody Card card) {
	
	try {
		card.setUpdatedAt(LocalDateTime.now());
		cardService.editCard(card);
		return new ResponseEntity<Card>(card,HttpStatus.ACCEPTED);
	} catch (CardNumberDoesNotExist e) {
		
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}

@DeleteMapping("/delete/{cardNo}")
public ResponseEntity<?> deleteCard(@PathVariable String cardNo) throws CardNumberDoesNotExist {
	cardService.deleteCard(cardNo);
	return new ResponseEntity<String>("Card successfully removed", HttpStatus.OK);
}

@GetMapping("/getcard/{accountNo}")
public ResponseEntity<?> getCardByAccountNumber(@PathVariable String accountNo) throws AccountNumberDoesNotExist  {
	try {
		return new ResponseEntity<List<Card>>(cardService.findByAccountNumber(accountNo), HttpStatus.OK);
	} catch (AccountNumberDoesNotExist e) {
		// TODO Auto-generated catch block
		return new ResponseEntity<String>("Account number does not exist", HttpStatus.CONFLICT);
	}
}

@GetMapping("/get")
public ResponseEntity<List<Card>> fetchAll() {
	return new ResponseEntity<List<Card>>(cardService.fetchAllCards(), HttpStatus.OK);
}
	

}

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.natwest.model.Accounts;
import com.natwest.service.AccountsService;

@RestController
@RequestMapping("/api/accounts")
public class AccountsController {

	@Autowired
	private AccountsService accountsService;
	
	@GetMapping("/get")
	public ResponseEntity<?> getAllAccounts() {
		
		List<Accounts> acclist = accountsService.getAllAcc();
		return new ResponseEntity<List<Accounts>>(acclist,HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Accounts> addAccounts(@RequestBody Accounts acc) {
		
		acc.setUpdatedAt(LocalDateTime.now());
		accountsService.addAccount(acc);
		
		return new ResponseEntity<Accounts>(acc,HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{accountType}/and/{customerId}")
	public ResponseEntity<?> getAllAccountsByAccountTypeAndCustomerId(@PathVariable String accountType, @PathVariable String customerId) {
		
		List<Accounts> acclist = accountsService.findByAccountTypeAndCustomerId(accountType, customerId);
		return new ResponseEntity<List<Accounts>>(acclist,HttpStatus.OK);
	}
	
	@GetMapping("/getaccount/{customerId}")
	public ResponseEntity<?> getAllAccountsByCustomerId(@PathVariable String customerId) {
		
		List<Accounts> acclist = accountsService.getByCustomerId(customerId);
		return new ResponseEntity<List<Accounts>>(acclist,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{accountNo}")
	public ResponseEntity<?> deleteAccount(@PathVariable String accountNo) {
		accountsService.deleteAccount(accountNo);
		return new ResponseEntity<String>("Account deleted",HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAll/{customerId}")
	public ResponseEntity<?> deleteAccountBycustomerId(@PathVariable String customerId) {
		accountsService.deleteAccountsByCustomerId(customerId);
		return new ResponseEntity<String>("Account deleted",HttpStatus.OK);
	}

	
	
}

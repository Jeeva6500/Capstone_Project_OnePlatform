package com.natwest.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.natwest.exceptions.AccountNoDoesNotExist;
import com.natwest.exceptions.TransactionIDAlreadyExist;
import com.natwest.exceptions.TransactionIDDoesNotExist;
import com.natwest.model.Transaction;
import com.natwest.service.TransactionService;

@RestController
@RequestMapping("/api/transaction")

public class TransactionController {
	@Autowired
	private TransactionService transactionService;
	
	//add transaction
	@PostMapping("/add")
	public ResponseEntity<?> addTrans(@RequestBody Transaction tran) {
		
		try {
			tran.setTransactionDate(LocalDateTime.now());
			transactionService.addTransaction(tran);
			return new ResponseEntity<Transaction>(tran, HttpStatus.CREATED);
		} catch (TransactionIDAlreadyExist e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	//update transaction
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateTrans(@PathVariable int id, @RequestBody Transaction tran) {
		
		try {
			tran.setTransactionDate(LocalDateTime.now());
			transactionService.editTransation(tran);
			return new ResponseEntity<Transaction>(tran,HttpStatus.ACCEPTED);
		} catch (TransactionIDDoesNotExist e) {
			
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
	//delete transaction
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteTrans(@PathVariable int id) throws TransactionIDDoesNotExist {
		transactionService.deleteTransaction(id);
		return new ResponseEntity<String>("Record successfully deleted", HttpStatus.OK);
	}
	
	//getTransaction By id
	@GetMapping("/gettransaction/{cardNo}")
	public ResponseEntity<?> getTransaction(@PathVariable String cardNo) throws AccountNoDoesNotExist {
		try {
			List<Transaction> s = transactionService.GetByCardNo(cardNo);
			System.out.println(s);
			return new ResponseEntity<List<Transaction>>(transactionService.GetByCardNo(cardNo), HttpStatus.OK);
		} catch (AccountNoDoesNotExist e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>("Account number does not exist", HttpStatus.CONFLICT);
		}
	}
 	
	//get all transaction
	@GetMapping("/get")
	public ResponseEntity<List<Transaction>> fetchAll() {
		return new ResponseEntity<List<Transaction>>(transactionService.fetchAllTransactions(), HttpStatus.OK);
	}

}

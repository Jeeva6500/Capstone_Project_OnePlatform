package com.natwest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = TransactionIDDoesNotExist.class)
	public ResponseEntity<String> transactionIDDoesNotExist() {
		return new ResponseEntity<>("Sorry, transaction Id not available, check again!", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = AccountNoDoesNotExist.class)
	public ResponseEntity<?> AccountNoDoesNotExist() {
		return new ResponseEntity<>("Sorry, check again. Account No. does not exist", HttpStatus.NOT_FOUND);
	}

}

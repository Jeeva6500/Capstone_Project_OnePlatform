package com.natwest.exceptions;

public class TransactionIDDoesNotExist extends Exception{
	
	public TransactionIDDoesNotExist(String msg) {
		super(msg);
	}
	
	public TransactionIDDoesNotExist() {}

}

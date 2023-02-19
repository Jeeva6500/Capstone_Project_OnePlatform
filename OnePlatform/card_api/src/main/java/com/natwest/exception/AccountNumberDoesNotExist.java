package com.natwest.exception;

public class AccountNumberDoesNotExist extends Exception{
	public AccountNumberDoesNotExist(String msg) {
		super(msg);
	}
	
	public AccountNumberDoesNotExist() {}

}

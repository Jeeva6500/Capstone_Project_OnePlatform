package com.natwest.exceptions;

public class AccountNoDoesNotExist extends Exception {
	
	public AccountNoDoesNotExist(String msg) {
		super(msg);
	}
	
	public AccountNoDoesNotExist() {}


}

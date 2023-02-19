package com.natwest.service;

import java.util.List;

import com.natwest.model.Accounts;

public interface AccountsService {

public Accounts addAccount(Accounts acc);
	
	public List<Accounts> getAllAcc();
	
	public void deleteAccount(String accountNo);
	
	public List<Accounts> findByAccountTypeAndCustomerId(String accountType, String customerId);
	
	public List<Accounts> getByCustomerId(String customerId);
	
	public void deleteAccountsByCustomerId(String customerId);

	
}

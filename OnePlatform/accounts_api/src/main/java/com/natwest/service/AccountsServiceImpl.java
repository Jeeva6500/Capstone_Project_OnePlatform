package com.natwest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.natwest.model.Accounts;
import com.natwest.repo.AccountsRepository;

@Service
public class AccountsServiceImpl implements AccountsService{

	@Autowired
	private AccountsRepository accountsRepo;
	
	@Override
	public Accounts addAccount(Accounts acc) {
		// TODO Auto-generated method stub
		return accountsRepo.save(acc);
	}

	@Override
	public List<Accounts> getAllAcc() {
		// TODO Auto-generated method stub
		return accountsRepo.findAll(Sort.by(Sort.Direction.ASC,"customerId"));
	}

	@Override
	public void deleteAccount(String accountNo) {
		accountsRepo.deleteById(accountNo);
		
	}

	@Override
	public List<Accounts> findByAccountTypeAndCustomerId(String accountType, String customerId) {
		// TODO Auto-generated method stub
		return accountsRepo.findByAccountTypeAndCustomerId(accountType, customerId);
	}

	@Override
	public List<Accounts> getByCustomerId(String customerId) {
		// TODO Auto-generated method stub
		return accountsRepo.getByCustomerId(customerId);
	}

	@Override
	public void deleteAccountsByCustomerId(String customerId) {
		accountsRepo.deleteByCustomerId(customerId);
		
	}

}

package com.natwest.service;

import java.util.List;



import com.natwest.exceptions.AccountNoDoesNotExist;
import com.natwest.exceptions.TransactionIDAlreadyExist;
import com.natwest.exceptions.TransactionIDDoesNotExist;
import com.natwest.model.Transaction;

public interface TransactionService {

	public boolean addTransaction(Transaction trn) throws TransactionIDAlreadyExist;
	public boolean editTransation(Transaction trn) throws TransactionIDDoesNotExist;
	public boolean deleteTransaction(int id) throws TransactionIDDoesNotExist;
	
	
	public List<Transaction> GetByCardNo(String cardNo) throws AccountNoDoesNotExist;
	
	public List<Transaction> fetchAllTransactions();
	public Transaction getTransactionById(int id) throws TransactionIDDoesNotExist;

}

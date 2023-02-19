package com.natwest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natwest.email.EmailSenderService;
import com.natwest.exceptions.AccountNoDoesNotExist;
import com.natwest.exceptions.TransactionIDAlreadyExist;
import com.natwest.exceptions.TransactionIDDoesNotExist;
import com.natwest.model.Transaction;
import com.natwest.repo.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepo;
	
	@Autowired
	private EmailSenderService senderService;

	@Override
	public boolean addTransaction(Transaction trn) throws TransactionIDAlreadyExist {
		
		Optional<Transaction> temp = transactionRepo.findById(trn.getTransactionId());
		
		if(temp.isPresent()) {
			throw new TransactionIDAlreadyExist("Cannot add, transaction already exists");
			
		} else {
			transactionRepo.save(trn);
			senderService.sendSimpleEmail("jeeva650@gmail.com", "Transaction confirmation on your Card", "Dear Customer, Your account has been debited for Rs."
			+trn.getTransactionAmount());
			System.out.println("Email Sent successfully...");

			return true;
		}
		
	}

	@Override
	public boolean editTransation(Transaction trn) throws TransactionIDDoesNotExist {
		
		Optional<Transaction> temp = transactionRepo.findById(trn.getTransactionId());
		
		if(temp.isPresent()) {
			transactionRepo.save(trn);
			return true;
		} else {
			throw new TransactionIDDoesNotExist("Sorry, transaction Id not available, check again!");
		}
		
	}

	@Override
	public boolean deleteTransaction(int id) throws TransactionIDDoesNotExist {
		Optional<Transaction> temp = transactionRepo.findById(id);
		if(temp.isPresent()) {
			transactionRepo.deleteById(id);
			return true;
		} else {
			throw new TransactionIDDoesNotExist();
		}
	}

	@Override
	public List<Transaction> GetByCardNo(String cardNo) throws AccountNoDoesNotExist {
		Optional<List<Transaction>> optlist = transactionRepo.GetByCardNo(cardNo);
		
		if(optlist.isPresent()) {
			
			List<Transaction> trlist = optlist.get();
			System.out.println(trlist);
			return trlist;
			
		} else {
			throw new AccountNoDoesNotExist("Sorry, Account no does not exist, check again!");
		}
		
	}

	@Override
	public List<Transaction> fetchAllTransactions() {
		// TODO Auto-generated method stub
		return transactionRepo.findAll();
	}

	@Override
	public Transaction getTransactionById(int id) throws TransactionIDDoesNotExist {
		Optional<Transaction> temp = transactionRepo.findById(id);
		if(temp.isPresent()) {
			return temp.get();
		} else {
			throw new TransactionIDDoesNotExist();
		}
		
	}

	
}

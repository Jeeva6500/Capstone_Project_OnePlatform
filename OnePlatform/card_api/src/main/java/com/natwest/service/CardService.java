package com.natwest.service;

import java.util.List;

import com.natwest.exception.AccountNumberDoesNotExist;
import com.natwest.exception.CardNumberAlreadyExist;
import com.natwest.exception.CardNumberDoesNotExist;
import com.natwest.model.Card;

public interface CardService {
	
	
public boolean addCard(Card card) throws CardNumberAlreadyExist;
public boolean editCard(Card card) throws CardNumberDoesNotExist;
public boolean deleteCard(String cardNo) throws CardNumberDoesNotExist;
public List<Card> findByAccountNumber(String accountNo) throws AccountNumberDoesNotExist;

public List<Card> fetchAllCards();
public Card getCardByCardNumber(String cardNo) throws CardNumberDoesNotExist;


}

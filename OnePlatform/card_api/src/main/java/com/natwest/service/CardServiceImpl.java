package com.natwest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natwest.exception.AccountNumberDoesNotExist;
import com.natwest.exception.CardNumberAlreadyExist;
import com.natwest.exception.CardNumberDoesNotExist;
import com.natwest.model.Card;
import com.natwest.repo.CardRepository;

@Service
public class CardServiceImpl implements CardService{

	@Autowired
	private CardRepository cardRepo;
	
	@Override
	public boolean addCard(Card card) throws CardNumberAlreadyExist {
		Optional<Card> temp = cardRepo.findById(card.getCardNo());
		if (temp.isPresent()) {
			throw new CardNumberAlreadyExist("Card number already exist!");
		}
		else {
			cardRepo.save(card);
			return true;
		}
	}

	@Override
	public boolean editCard(Card card) throws CardNumberDoesNotExist {
		Optional<Card> temp = cardRepo.findById(card.getCardNo());
		if (temp.isPresent()) {
			cardRepo.save(card);
			return true;
		}
		else {
			throw new CardNumberDoesNotExist("Card number not exist!");
		}
	}

	@Override
	public boolean deleteCard(String cardNo) throws CardNumberDoesNotExist {
		Optional<Card> temp = cardRepo.findById(cardNo);
		if (temp.isPresent()) {
			cardRepo.deleteById(cardNo);
			return true;
		}
		
		else {
			throw new CardNumberDoesNotExist("Card number not exist!");
		}
	}

	@Override
	public List<Card> findByAccountNumber(String accountNo) throws AccountNumberDoesNotExist {
		Optional<List<Card>> optionalList = cardRepo.findByAccountNo(accountNo);
		if (optionalList.isPresent()) {
			List<Card> cardList = optionalList.get();
			return cardList;
		}
		else {
			throw new AccountNumberDoesNotExist("The account number soes not exist!");
		}
	}

	@Override
	public List<Card> fetchAllCards() {
		return cardRepo.findAll();
	}

	@Override
	public Card getCardByCardNumber(String cardNo) throws CardNumberDoesNotExist {
		Optional<Card> temp = cardRepo.findById(cardNo);
		if (temp.isPresent()) {
			return (Card) temp.get();
		}
		else {
			throw new CardNumberDoesNotExist("Card number not exist!");
		}
			}

}

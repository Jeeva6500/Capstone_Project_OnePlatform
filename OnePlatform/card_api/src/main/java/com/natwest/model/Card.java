package com.natwest.model;

import java.time.LocalDate;
import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Card {
	
	@Id
	@Column(length = 18)
    @Size(min = 8, max = 18)
	private String cardNo;
	@Column(nullable = false)
	private double cardLimit;
	@Column(nullable = false)
	private double cardSpent;
	@Column(nullable = false)
	private double cardBalance;
	private LocalDateTime updatedAt;
	private LocalDate expiryDate;
	private String cvv;
	@Column(nullable = false)
	private String accountNo;
	
	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Card(@Size(min = 8, max = 18) String cardNo, double cardLimit, double cardSpent, double cardBalance,
			LocalDateTime updatedAt, LocalDate expiryDate, String cvv, String accountNo) {
		super();
		this.cardNo = cardNo;
		this.cardLimit = cardLimit;
		this.cardSpent = cardSpent;
		this.cardBalance = cardBalance;
		this.updatedAt = updatedAt;
		this.expiryDate = expiryDate;
		this.cvv = cvv;
		this.accountNo = accountNo;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public double getCardLimit() {
		return cardLimit;
	}

	public void setCardLimit(double cardLimit) {
		this.cardLimit = cardLimit;
	}

	public double getCardSpent() {
		return cardSpent;
	}

	public void setCardSpent(double cardSpent) {
		this.cardSpent = cardSpent;
	}

	public double getCardBalance() {
		return cardBalance;
	}

	public void setCardBalance(double cardBalance) {
		this.cardBalance = cardBalance;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	@Override
	public String toString() {
		return "Card [cardNo=" + cardNo + ", cardLimit=" + cardLimit + ", cardSpent=" + cardSpent + ", cardBalance="
				+ cardBalance + ", updatedAt=" + updatedAt + ", expiryDate=" + expiryDate + ", cvv=" + cvv
				+ ", accountNo=" + accountNo + "]";
	}

	
	

}

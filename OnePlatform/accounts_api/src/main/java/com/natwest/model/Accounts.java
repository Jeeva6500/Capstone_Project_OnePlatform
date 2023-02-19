package com.natwest.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Accounts {
	@Id
	@Column(length = 18)
    @Size(min = 8, max = 18)
	private String accountNo;
	@Column(nullable = false)
	private String accountType;
	@Column(nullable = false)
	private String bankName;
	private String ifscCode;	
	private String balance;
	private LocalDateTime updatedAt;
	@Column(nullable = false)
	private String customerId;
	
	public Accounts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Accounts(@Size(min = 8, max = 18) String accountNo, String accountType, String bankName, String ifscCode,
			String balance, LocalDateTime updatedAt, String customerId) {
		super();
		this.accountNo = accountNo;
		this.accountType = accountType;
		this.bankName = bankName;
		this.ifscCode = ifscCode;
		this.balance = balance;
		this.updatedAt = updatedAt;
		this.customerId = customerId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Accounts [accountNo=" + accountNo + ", accountType=" + accountType + ", bankName=" + bankName
				+ ", ifscCode=" + ifscCode + ", balance=" + balance + ", updatedAt=" + updatedAt + ", customerId="
				+ customerId + "]";
	}

	
	
	
	}

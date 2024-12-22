package com.example.bankservice.entities;

import java.util.Objects;

//Format to store data in a sequence for Fund Transfer from one account to another
public class FundTransfer {
	//Variable storing sender's accountNumber;
	private Long senderAccount;
	//Variable storing receiver's accountNumber;
	private Long receiverAccount;
	//Variable storing amount to be sent
	private Long balance;
	public Long getSenderAccount() {
		return senderAccount;
	}
	public void setSenderAccount(Long senderAccount) {
		this.senderAccount = senderAccount;
	}
	public Long getReceiverAccount() {
		return receiverAccount;
	}
	public void setReceiverAccount(Long receiverAccount) {
		this.receiverAccount = receiverAccount;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	public FundTransfer(Long senderAccount, Long receiverAccount, Long balance) {
		super();
		this.senderAccount = senderAccount;
		this.receiverAccount = receiverAccount;
		this.balance = balance;
	}
	public FundTransfer() {
		super();
		// TODO Auto-generated constructor stub
	}
}

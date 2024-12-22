package com.example.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userservice.entities.FundTransfer;
import com.example.userservice.entities.Funds;
import com.example.userservice.entities.Login;
import com.example.userservice.entities.User;
import com.example.userservice.repository.BalanceRepository;
import com.example.userservice.repository.LoginRepository;
import com.example.userservice.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
//Handling service related to funds of the user
public class FundService {

	@Autowired
	BalanceRepository balanceRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	LoginRepository loginRepo;
	

	@Transactional
	public String transferFund(FundTransfer transaction) {
		//Searching for the sender information
		User sender = userRepo.findById(transaction.getSenderAccount()).orElse(null);
		//Returning message if no information found
		if(sender==null) return "Sender not found";
		//Searching for the receiver information
		User receiver = userRepo.findById(transaction.getReceiverAccount()).orElse(null);
		//Returning message if no information found
		if(receiver==null) return "Receiver not found";
		//Searching for the balance information of the sender
		Funds senderFund = balanceRepo.findByUser(sender).orElse(null);
		//Returning message if available balance is less than transaction amount
		if(senderFund.getBalance()<transaction.getBalance()) return "Fund not available";
		senderFund.setBalance(senderFund.getBalance()-transaction.getBalance());
		//Searching for the balance information of the receiver
		Funds receiverFund = balanceRepo.findByUser(receiver).orElse(null);
		receiverFund.setBalance(receiverFund.getBalance()+transaction.getBalance());
		//Updating the balance of sender and receiver
		balanceRepo.save(senderFund);
		balanceRepo.save(receiverFund);
		//Returning success message after successful transaction
		return "Money transferred successfully";
	}
	
	public Long getBalance(String userId) {
		//Getting the accountNumber from userId
		Login login = loginRepo.findById(userId).orElse(null);
		User user = login.getUser();
		//Searching for the balance using accountNumber
		Funds avlBalance = balanceRepo.findByUser(user).orElse(null);
		if(avlBalance==null) return 0L;
		//Returning available balance
		return avlBalance.getBalance();
	}
}

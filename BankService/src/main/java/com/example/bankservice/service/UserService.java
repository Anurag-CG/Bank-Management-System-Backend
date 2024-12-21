package com.example.bankservice.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankservice.entities.FundTransfer;
import com.example.bankservice.entities.Funds;
import com.example.bankservice.entities.Login;
import com.example.bankservice.entities.User;
import com.example.bankservice.repository.*;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	LoginRepository loginRepo;
	@Autowired
	BalanceRepository balanceRepo;
	
	public String addUser(User user) {
		//Randomly generating a new number within the range
		Random random = new Random();
        long min = 7134500000L;
        long max = 9759000000L;
        long accountNo = min + (long) (random.nextDouble() * (max - min));
        //Regenerating the number until a unique number is formed
        while (userRepo.existsById(accountNo)) {
        	accountNo = min + (long) (random.nextDouble() * (max - min));
        }
        user.setAccountNumber(accountNo);
        //Checking if the Aadhar number already exists or not
        if(userRepo.existsByAadharNumber(user.getAadharNumber())) return "Aadhar Number already exists";
        //Checking if the PAN number already exists or not
        if(userRepo.existsByPanNumber(user.getPanNumber())) return "PAN Number already exists";
        //Creating a record of the fund for the user
        Funds balanceAccount  = new Funds();
        balanceAccount.setUser(user);
        balanceAccount.setBalance(0L);
        //Saving the User in Fund Repository with 0 balance
        balanceRepo.save(balanceAccount);
        //Saving the user in User Repository
		userRepo.save(user);
		return "Account created successfully\nAccount No. : "+accountNo;
	}
	
	public String deleteUser(long accountNumber, long aadharNumber) {
		//Searching the user to delete from the record
		User user = userRepo.findById(accountNumber).orElse(null);
		//Returning message if no user found with given account number
		if(user==null)  return "Please enter correct account number";
		long userAadharNumber = user.getAadharNumber();
		//Returning message if Aadhar number not matches with record
		if(userAadharNumber!=aadharNumber) {
			return "Aadhar Number not matched!";
		}
		//Deleting NetBanking Credentials if present
		Login login = loginRepo.findByUser(user);
		if(login!=null) {
			loginRepo.delete(login);
		}
		//Searching for the available fund to return after account deletion
		Funds avlBalance = balanceRepo.findByUser(user).orElse(null);
		//Returning message if can't find the amount information
		if(avlBalance==null) return "Error occured while getting the balance information";
		Long balance = avlBalance.getBalance();
		//Deleting the record of the balance of that user
		balanceRepo.delete(avlBalance);
		//Deleting the record of the user
		userRepo.delete(user);
		//Returning the message with balance to be returned
		return "User deleted.\nPlease return the amount: Rs."+balance;
	}

	public User findUser(Long accountNumber) {
		//Searching for the user with given accountNumber
		User foundUser = userRepo.findById(accountNumber).orElse(null);
		return foundUser;
	}

	public User updateUser(User user) {
		//Updating user information in the table
		User updatedUser = userRepo.save(user);
		return updatedUser;
	}
	
	@Transactional
	public String transferFund(FundTransfer transaction) {
		//Searching the sender information from User table		
		User senderAccount = userRepo.findById(transaction.getSenderAccount()).orElse(null);
		//Returning message if account number is not correct
		if(senderAccount==null) return "Please enter a valid sender account";
		else {
			//Searching for the receiver details from User table
			User receiverAccount = userRepo.findById(transaction.getReceiverAccount()).orElse(null);
			//Returning message if receiver's account number is not correct
			if(receiverAccount==null) return "Please enter a valid Receiver account";
			else {
				//Storing the amount to process later
				Long amount = transaction.getBalance();
				//Searching for the available fund of the sender
				Funds senderBalance = balanceRepo.findByUser(senderAccount).orElse(null);
				//Returning message if the available balance is less than amount
				if(senderBalance.getBalance()<amount) return "Amount not available";
				else {
					Long balanceLeft = senderBalance.getBalance()-amount;
					senderBalance.setBalance(balanceLeft);
					//Updating the balance for the sender
					balanceRepo.save(senderBalance);
					Funds receiverBalance = balanceRepo.findByUser(receiverAccount).orElse(null);
					receiverBalance.setBalance(receiverBalance.getBalance()+amount);
					//Updating the balance for the receiver
					balanceRepo.save(receiverBalance);
					//Sending success message
					return "Transaction done successfully";
				}
			}
		}
	}

	@Transactional
	public String addFund(Long amount, Long accountNumber) {
		//Searching for the user by given accountNumber
		User user = userRepo.findById(accountNumber).orElse(null);
		//Returning message if user not found in records
		if(user==null) return "User not found";
		//Searching for the balance information of the user
		Funds beneficiary = balanceRepo.findByUser(user).orElse(null);
		//Returning message if balance record not found for the user
		if(beneficiary==null) return "Balance record not found";
		beneficiary.setBalance(amount+beneficiary.getBalance());
		//Updating the balance of the user
		balanceRepo.save(beneficiary);
		//Sending the success message
		return "Amount credited successfully";
	}
	
	@Transactional
	public String debitFund(Long amount, Long accountNumber) {
		//Searching for the user by given accountNumber
		User user = userRepo.findById(accountNumber).orElse(null);
		//Returning message if user not found in records
		if(user==null) return "User not found";
		//Searching for the balance information of the user
		Funds beneficiary = balanceRepo.findByUser(user).orElse(null);
		//Returning message if transaction can't be performed due to unavailability of funds
		if(beneficiary.getBalance()<amount) return "Amount not available";
		beneficiary.setBalance(beneficiary.getBalance()-amount);
		//Updating the balance of the user
		balanceRepo.save(beneficiary);
		//Sending the success message
		return "Money debited";
	}

	public Long viewFund(Long accountNumber) {
		//Searching for the user with given accountNumber;
		User user = userRepo.findById(accountNumber).orElse(null);
		//returning null if user not found
		if(user==null) return null;
		//Searching for fund of the user
		Funds fund = balanceRepo.findByUser(user).orElse(null);
		//Returning null if no record found
		if(fund==null) return null;
		//Returning the balance found from table
		return fund.getBalance();
	}
}

package com.example.bankservice.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
 
import com.example.bankservice.entities.Login;
import com.example.bankservice.entities.User;
import com.example.bankservice.repository.*;
 
@Service
public class LoginService {
 
	@Autowired
	LoginRepository loginRepo;
	@Autowired
	UserRepository userRepo;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	public String addLogin(Long accountNumber, Login login) {
		//Searching for the given userId
		Login findLogin = loginRepo.findById(login.getUserId()).orElse(null);
		//If found, sending message
		if(findLogin!=null)  return "UserId not available";
		//Searching for the account Number for full information
		User user = userRepo.findById(accountNumber).orElse(null);
		//Returning message if user information not found
		if(user==null) return "Please enter valid account number";
		//Searching if any another userId is present with same account Number
		if(loginRepo.findByUser(user)!=null) return "LoginId already present";
		//Setting the user for the login
		login.setUser(user);
		//Encoding the password to save in record
		login.setPassword(encoder.encode(login.getPassword()));
		//Saving the login credentials to the table
		loginRepo.save(login);
		//Returning success message after saving the information
		return "UserId created successfully";
	}
	
	public String deleteLogin(Long accountNumber, String userId) {
		//Searching for the login information
		Login login = loginRepo.findById(userId).orElse(null);
		//Returning message if no information found
		if(login==null) return "LoginId not valid";
		long deleteLoginAccount = login.getUser().getAccountNumber();
		//Returning message if accountNumber not matched with accountNumber in record for the userId
		if(deleteLoginAccount!=accountNumber) return "Account number not matching";
		//Deleting the login record
		loginRepo.delete(login);
		//Returning success message
		return "User deleted successfully";
	}
}
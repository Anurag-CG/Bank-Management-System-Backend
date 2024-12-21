package com.example.userservice.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.userservice.repository.BalanceRepository;
import com.example.userservice.repository.LoginRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.entities.Funds;
import com.example.userservice.entities.Login;
import com.example.userservice.entities.LoginForm;
import com.example.userservice.entities.UpdateLoginForm;
import com.example.userservice.entities.User;

@Service
//Handling all request related to profile
public class ProfileService {
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	LoginRepository loginRepo;
	@Autowired
	BalanceRepository balanceRepo;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	public String addUser(User user) {
		//Generating random accountNumber for the user
		Random random = new Random();
        long min = 7134500000L;
        long max = 9759000000L;
        long accountNo = min + (long) (random.nextDouble() * (max - min));
        while (userRepo.existsById(accountNo)) {
        	accountNo = min + (long) (random.nextDouble() * (max - min));
        }
        user.setAccountNumber(accountNo);
        //Searching and returning message if Aadhar Number already present in table
        if(userRepo.existsByAadharNumber(user.getAadharNumber())) return "Aadhar Number already exists";
        //Searching and returning message if PAN Number already present in table
        if(userRepo.existsByPanNumber(user.getPanNumber())) return "PAN Number already exists";
        //Creating fund record for the user
        Funds balanceAccount  = new Funds();
        balanceAccount.setUser(user);
        balanceAccount.setBalance(0L);
        balanceRepo.save(balanceAccount);
        //Saving user details in the table
		userRepo.save(user);
		//Returning success message with Account Number generated randomly
		return "Acccount created.\nAccount No. : "+accountNo;
	}
	
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		User updatedUser = userRepo.save(user);
		return updatedUser;
	}
	
	public Login updateLogin(UpdateLoginForm form) {
		//Searching for login information of user
		Login currentLogin = loginRepo.findById(form.getUserId()).orElse(null);
		//Validating if the password is correct
		if(encoder.matches(form.getPreviousPassword(), currentLogin.getPassword())) {
			currentLogin.setPassword(encoder.encode(form.getNewPassword()));
			//Updating the password
			loginRepo.save(currentLogin);
		} else {
			//Returning null if password not matched
			return null;
		}
		return currentLogin;
	}
	
	public User getAccount(Login login) {
		//Searching for the record for given loginId
		Login loginUser = loginRepo.findById(login.getUserId()).orElse(null);
		//Searching for the user with given loginId
		User foundUser = userRepo.findById(loginUser.getUser().getAccountNumber()).orElse(null);
		//Returning the user found
		return foundUser;
	}

	public String addLogin(LoginForm login) {
		//Searching for the User using accountNumber
		User user = userRepo.findById(login.getAccountNumber()).orElse(null);
		//Returning message if no user found
		if(user==null) return "Please enter valid Account Number";
		//Validating the Aadhar number with user information
		//If not validated, returning message
		if(!user.getAadharNumber().equals(login.getAadharNumber())) return "Aadhar Not Matched";
		//Searching if the userId is available for same account
		Login loginData = loginRepo.findByUser(user);
		//If available, returning message
		if(loginData!=null) return "Account already exists";
		//Searching for the availability of the userId
		Login alreadyPresentId = loginRepo.findById(login.getLoginId()).orElse(null);
		//Returning message if userId already used
		if(alreadyPresentId!=null) return "UserId not available";
		//Encoding password
		//login.setPassword(encoder.encode(login.getPassword()));
		//Creating record for the login credentials
		login.setPassword(encoder.encode(login.getPassword()));
		Login credentials = new Login(login.getLoginId(), user, login.getPassword());
		System.out.println(credentials.getUserId()+" "+credentials.getPassword());
		loginRepo.save(credentials);
		//Returning success message once stored
		return "LoginId created successfully";
	}
}

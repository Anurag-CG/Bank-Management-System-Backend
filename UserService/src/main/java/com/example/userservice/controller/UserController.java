package com.example.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.userservice.entities.FundTransfer;
import com.example.userservice.entities.Login;
import com.example.userservice.entities.LoginForm;
import com.example.userservice.entities.UpdateLoginForm;
import com.example.userservice.entities.User;
import com.example.userservice.service.FundService;
import com.example.userservice.service.ProfileService;

@Controller
@RequestMapping("/user")
//Handling all requests with URL "/user"
public class UserController {

	@Autowired
	FundService fundService;
	@Autowired
	ProfileService profileService;
	
	//Handling user request to create new account
	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody User user) {
		//Calling addUser method of profileService
		String savedUser = profileService.addUser(user);
		//Returning message with Bad_Request if user not added
		if(savedUser.contains("exists")) return new ResponseEntity<>(savedUser, HttpStatus.BAD_REQUEST);
		//Returning message with OK after adding user
		return new ResponseEntity<>(savedUser, HttpStatus.OK);
	}
	
	//Handling user request to create LoginId
	@PostMapping("/addLogin")
	public ResponseEntity<String> addLogin(@RequestBody LoginForm login) {
		//Calling addLogin method of profileService
		String message = profileService.addLogin(login);
		//Returning message with OK if login added
		if(message.contains("successfully")) return new ResponseEntity<>(message, HttpStatus.OK);
		//Returning message with Bad_Request if login added
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
	
	//Handling user request to update details
	@PutMapping("/update")
	ResponseEntity<User> updateUser(@RequestBody User user) {
		User updatedUser = profileService.updateUser(user);
		if(updatedUser==null) return new ResponseEntity<>(updatedUser, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	//Handling user to get details
	@PostMapping("/findUser")
	ResponseEntity<User> getUser(@RequestBody Login login) {
		//Calling getAccount method of profileService
		User foundUser = profileService.getAccount(login);
		//Returning Bad_Request if not found user
		if(foundUser==null) return new ResponseEntity<>(foundUser, HttpStatus.BAD_REQUEST);
		//Returning OK with user details if found
		return new ResponseEntity<>(foundUser, HttpStatus.OK);
	}
	
	//Handling user request to view Balance
	@GetMapping("/viewBalance")
	ResponseEntity<Object> availableBalance(@RequestParam("userId") String userId) {
		//Returning the value returned from getBalance method of fundService
		Long balance = fundService.getBalance(userId);
		return new ResponseEntity<>(balance, HttpStatus.OK);
	}
	
	//Handling user request to update Login password
	@PutMapping("/updatePassword")
	ResponseEntity<Login> updatePassword(@RequestBody UpdateLoginForm form) {
		//Calling updateLogin method of profileService
		Login updatedLogin = profileService.updateLogin(form);
		//Returning Bad_Request if couldn't find the Login information
		if(updatedLogin==null) return new ResponseEntity<>(updatedLogin, HttpStatus.BAD_REQUEST);
		//Returning OK if password updated
		return new ResponseEntity<>(updatedLogin, HttpStatus.OK);
	}
	
	//Handling user request for fund transfer
	@PostMapping("/fund_transfer")
	ResponseEntity<String> transferFund(@RequestBody FundTransfer transaction) {
		//Calling transferFund method of fundService
		String txn = fundService.transferFund(transaction);
		//Returning Bad_Reqeust if transaction not done
		if(txn.contains("not")) return new ResponseEntity<>(txn, HttpStatus.BAD_REQUEST);
		//Returning OK if transaction done successfully
		return new ResponseEntity<>(txn, HttpStatus.OK);
	}
	
	
}

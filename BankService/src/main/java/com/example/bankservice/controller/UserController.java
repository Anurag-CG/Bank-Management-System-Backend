package com.example.bankservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bankservice.entities.FundTransfer;
import com.example.bankservice.entities.User;
import com.example.bankservice.service.UserService;

@Controller
@RequestMapping("/bank")
public class UserController {

	@Autowired
	UserService userService;

	// Adding the user details to the database
	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody User user) {
		// Calling addUser method from userService
		String message = userService.addUser(user);
		// Returning Bad_Request if user not added
		if (!message.contains("successfully"))
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		// Returning OK if user added
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	// Deleting the user in the database
	@DeleteMapping("/deleteUser")
	public ResponseEntity<String> deleteUser(@RequestParam("accountNumber") long accountNumber,
			@RequestParam("aadharNumber") long aadharNumber) {
		// Calling the deleteUser method from userService
		String message = userService.deleteUser(accountNumber, aadharNumber);
		// Returning Bad_Request if user not deleted
		if (!message.contains("Please return the amount:"))
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		// Returning OK if user deleted
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	// Searching the user with given accountNumber
	@GetMapping("/findUser")
	public ResponseEntity<Object> findUser(@RequestParam("accountNumber") Long accountNumber) {
		// Calling the findUser method from userService
		User foundUser = userService.findUser(accountNumber);
		// Returning Bad_Request if can't find the user
		if (foundUser == null)
			return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
		// Returning OK with user details
		return new ResponseEntity<>(foundUser, HttpStatus.OK);
	}

	// Updating the user information
	@PutMapping("/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		// Calling the updateUser method of userService
		User updatedUser = userService.updateUser(user);
		// Returning OK after updating user information
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	// Fetching balance available for the user
	@GetMapping("/viewBalance")
	public ResponseEntity<Object> viewFund(@RequestParam("accountNumber") Long accountNumber) {
		// Calling the viewFund method of userService
		Long balance = userService.viewFund(accountNumber);
		// Returning message if ouuldn't find the balance
		if (balance == null)
			return new ResponseEntity<>("Error faced fetching balance", HttpStatus.BAD_REQUEST);
		// Returning OK with balance to be displayed
		return new ResponseEntity<>(balance, HttpStatus.OK);

	}

	// Transferring funds from one account to another
	@PostMapping("/fundtransfer")
	public ResponseEntity<String> fundTransfer(@RequestBody FundTransfer transaction) {
		// Calling the transferFund method of userService
		String str = userService.transferFund(transaction);
		// Returning OK if transaction done
		if (str.contains("successfully"))
			return new ResponseEntity<>(str, HttpStatus.OK);
		// Returning Bad_Request if transaction not done
		else
			return new ResponseEntity<>(str, HttpStatus.BAD_REQUEST);
	}

	// Adding fund to the user
	@PostMapping("/credit")
	public ResponseEntity<Object> creditTransaaction(@RequestParam("amount") Long amount,
			@RequestParam("accountNumber") Long accountNumber) {
		// Calling addFund method of userService
		String message = userService.addFund(amount, accountNumber);
		// Returning Bad_Request if amount not credited
		if (message.contains("not"))
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		// Returning OK if amount credited
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	// Withdrawing amount from user funds
	@PostMapping("/debit")
	public ResponseEntity<String> debitTransaction(@RequestParam("amount") Long amount,
			@RequestParam("accountNumber") Long accountNumber) {
		// Calling debitFund method of userService
		String str = userService.debitFund(amount, accountNumber);
		// Returning Bad_Request if transaction not performed
		if (str.contains("not"))
			return new ResponseEntity<>(str, HttpStatus.BAD_REQUEST);
		// Returning OK if amount debited
		return new ResponseEntity<>(str, HttpStatus.OK);
	}
}

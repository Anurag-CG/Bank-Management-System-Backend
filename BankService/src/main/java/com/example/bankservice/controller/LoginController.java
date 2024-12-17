package com.example.bankservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bankservice.entities.Login;
import com.example.bankservice.service.LoginService;

@Controller
@RequestMapping("/bank")
public class LoginController {

	@Autowired
	private LoginService loginService;

	// Adding Login information to the Login Repository
	@PostMapping("/addLogin")
	public ResponseEntity<String> addLogin(@RequestParam("accountNumber") Long accountNumber,
			@RequestBody Login login) {
		// Calling the addLogin method of loginService
		String savedLogin = loginService.addLogin(accountNumber, login);
		// Returning Bad_Request if can't add the information
		if (!savedLogin.contains("successfully")) {
			return new ResponseEntity<>(savedLogin, HttpStatus.BAD_REQUEST);
		}
		// Returning OK if added the information
		return new ResponseEntity<>(savedLogin, HttpStatus.OK);
	}

	// Deleting Login information of the user
	@DeleteMapping("/deleteLogin")
	public ResponseEntity<String> deleteLogin(@RequestParam("accountNumber") Long accountNumber,
			@RequestParam("userId") String userId) {
		// Calling deleteLogin method of loginService
		String deletedLogin = loginService.deleteLogin(accountNumber, userId);
		// Returning Bad_Request if deletion not done
		if (!deletedLogin.contains("successfully")) {
			return new ResponseEntity<>(deletedLogin, HttpStatus.BAD_REQUEST);
		}
		// Returning OK if deleted the login information
		return new ResponseEntity<>(deletedLogin, HttpStatus.OK);
	}
}

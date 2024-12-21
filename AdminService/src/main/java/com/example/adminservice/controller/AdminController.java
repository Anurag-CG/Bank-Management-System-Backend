package com.example.adminservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.adminservice.entities.BankerLogin;
import com.example.adminservice.entities.User;
import com.example.adminservice.service.AdminServices;

@Controller
@RequestMapping("/admin")
//Handling all requests with URL "/admin"
public class AdminController {
	
	@Autowired	
	AdminServices adminServices;
	
	//Handling admin request to create new banker
	@PostMapping("/addBanker")
	ResponseEntity<String> addBanker(@RequestBody BankerLogin banker) {
		//Calling addBanker method of adminService
		String message = adminServices.addBanker(banker);
		//Sending Bad_Request if banker not added
		if(message.contains("Error")) return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		//Sending OK if banker added successfully
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	//Handling admin request to delete banker
	@DeleteMapping("/removeBanker")
	ResponseEntity<String> removeBanker(@RequestBody BankerLogin banker) {
		//Calling removeBanker method of adminService
		String message = adminServices.removeBanker(banker);
		//Returning Bad_Request if banker not removed
		if(message.contains("not")) return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		//Returning OK if banker removed
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	//Handling admin request to view banker
	@GetMapping("/viewBanker")
	ResponseEntity<BankerLogin> showBanker(@RequestParam("bankerId") String bankerId) {
		//Calling seaarchBanker method of adminService
		BankerLogin searchedBanker = adminServices.searchBanker(bankerId);
		//Returning Bad_Request if banker not found
		if(searchedBanker==null) return new ResponseEntity<>(searchedBanker, HttpStatus.BAD_REQUEST);
		//Returning OK if banker found with given bankerId
		return new ResponseEntity<>(searchedBanker, HttpStatus.OK);
	}
	
	//Handling admin request to view user
	@GetMapping("/viewUser")
	ResponseEntity<User> searchUser(@RequestParam("accountNo") Long accountNumber) {
		//Calling searchUser method of adminService
		User foundUser = adminServices.searchUser(accountNumber);
		//Returning Bad_Request if user not found
		if(foundUser==null) return new ResponseEntity<>(foundUser, HttpStatus.BAD_REQUEST);
		//Returning OK if the user found with given account number
		return new ResponseEntity<>(foundUser, HttpStatus.OK);
	}
}

package com.example.loginservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.loginservice.entities.BankerLogin;
import com.example.loginservice.entities.Login;
import com.example.loginservice.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@GetMapping("/")
	public String working() {
		return "Working !";
	}

	@GetMapping("/login")
	public String loginWorking() {
		return "Login Working !";
	}

	@PostMapping("/bank/login")
	public ResponseEntity<BankerLogin> bankerLogin(@RequestBody BankerLogin loginCredential) {
		BankerLogin validated = loginService.validateUser(loginCredential);
		if (validated == null)
			return new ResponseEntity<>(validated, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(validated, HttpStatus.OK);
	}

	@PostMapping("/user/login")
	public ResponseEntity<Login> userLogin(@RequestBody Login loginCredential) {
		Login validated = loginService.validateLogin(loginCredential);
		if (validated == null)
			return new ResponseEntity<>(validated, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(validated, HttpStatus.OK);
	}

}

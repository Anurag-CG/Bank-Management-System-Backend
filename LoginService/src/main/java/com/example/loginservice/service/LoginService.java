package com.example.loginservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loginservice.entities.BankerLogin;
import com.example.loginservice.entities.Login;
import com.example.loginservice.repository.BankerRepository;
import com.example.loginservice.repository.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	private BankerRepository bankerRepo;
	@Autowired
	private LoginRepository loginRepo;

	public BankerLogin validateUser(BankerLogin loginCredential) {
		// TODO Auto-generated method stub
		BankerLogin user = bankerRepo.findById(loginCredential.getBankerId()).orElse(null);
		if(user == null) return null;
		if(user.getPassword()!=loginCredential.getPassword()) return null;
		return user;
	}

	public Login validateLogin(Login loginCredential) {
		// TODO Auto-generated method stub
		Login user = loginRepo.findById(loginCredential.getUserId()).orElse(null);
		if(user==null) return null;
		if(user.getPassword()!=loginCredential.getPassword()) return null;
		return user;
	}

}

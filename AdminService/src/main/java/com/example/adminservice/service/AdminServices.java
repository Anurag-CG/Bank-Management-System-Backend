package com.example.adminservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.adminservice.entities.BankerLogin;
import com.example.adminservice.entities.User;
import com.example.adminservice.repository.BankerRepository;
import com.example.adminservice.repository.UserRepository;

@Service
public class AdminServices {
	
	@Autowired
	BankerRepository bankerRepo;
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	private IDGeneratorService idGeneratorService;
	
	private PasswordEncoder encoder = new BCryptPasswordEncoder(12);
	 
	public String addBanker(BankerLogin banker) {
		//Generating bankerId using date
		String id = idGeneratorService.generateID();
        
        banker.setBankerId(id);
        //Checking if bankerId is already present or not
        //Although it must not be present, still performing a check
		BankerLogin bankerFound = bankerRepo.findById(id).orElse(null);
		//If already present, sending message
		if(bankerFound!=null) return "Error creating banker";
		//Encoding and setting the password for storing
		banker.setPassword(encoder.encode(banker.getPassword()));
		bankerRepo.save(banker);
		return "Banker Created successfully\nBanker Id: "+id;
	}

	public String removeBanker(BankerLogin banker) {
		//Searching for the banker with given bankerId
		BankerLogin targetBanker = bankerRepo.findById(banker.getBankerId()).orElse(null);
		//Returning message if bankerId already present
		if(targetBanker==null) return "Banker Id not matched";
		if(banker.getBankerName().equals(targetBanker.getBankerName())) {
			bankerRepo.delete(targetBanker);
			//Returning success message if banker deleted
			return "Banker deleted successfully";
		}
		//Returning message if banker not deleted
		return "Banker details not matched";
	}
	
	public BankerLogin searchBanker(String bankerId) {
		//Searching for the banker using bankerId
		BankerLogin foundBanker = bankerRepo.findById(bankerId).orElse(null);
		//Returning the found banker details
		return foundBanker;
	}

	public User searchUser(Long accountNo) {
		//Searching for the user using accountNumber
		User foundUser = userRepo.findById(accountNo).orElse(null);
		//Returning null, if the user not found
		if(foundUser==null) return null;
		//Returning the found user details
		return foundUser;
	}
}

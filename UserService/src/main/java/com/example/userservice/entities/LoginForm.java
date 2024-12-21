package com.example.userservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//Format to store data in a sequence for LoginId creation of the user
public class LoginForm {
	//Variable storing user's accountNumber
	private Long accountNumber;
	//Variable storing user's aadharNumber
	private Long aadharNumber;
	//variable storing loginId user wish to have
	private String loginId;
	//Variable storing the password to login
	private String password;
}

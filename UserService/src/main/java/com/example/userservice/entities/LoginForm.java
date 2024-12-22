package com.example.userservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Long getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(Long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginForm(Long accountNumber, Long aadharNumber, String loginId, String password) {
		super();
		this.accountNumber = accountNumber;
		this.aadharNumber = aadharNumber;
		this.loginId = loginId;
		this.password = password;
	}
	public LoginForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

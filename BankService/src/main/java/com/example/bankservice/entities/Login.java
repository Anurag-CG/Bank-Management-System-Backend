package com.example.bankservice.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
//Format to store the Login information in table
public class Login {
	
	@Id
	//Variable to store the userId of User
	//Marked as primary key to avoid conflict
	private String userId;
	//Marked as foreign key to avoid storing information for incorrect account
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	//Variable to store the accountNumber of the user
	@JoinColumn(name = "accountNumber")
	private User user;
	//Variable to store the password
	private String password;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Login(String userId, User user, String password) {
		super();
		this.userId = userId;
		this.user = user;
		this.password = password;
	}
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}


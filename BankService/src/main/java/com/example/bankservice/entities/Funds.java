package com.example.bankservice.entities;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
//Format to save Balance Records of User
public class Funds {
	//Auto Generated Id to be set as primary key since it is necessary for an Entity in Spring
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//Using accountNumber as a foreign key to avoid invalid account balance record
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "accountNumber")
	//Variable to store account_number
	private User user;
	//Variable to store balance
	private Long balance;
	
	//Constructor to create Fund and store them in table
	public Funds(User user, Long balance) {
		super();
		this.user = user;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Funds() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}

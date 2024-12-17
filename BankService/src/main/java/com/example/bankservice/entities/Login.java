package com.example.bankservice.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
}


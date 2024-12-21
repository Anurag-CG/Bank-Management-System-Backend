package com.example.userservice.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//Format to save Balance Records of User
public class Funds {
	//Auto Generated Id to be set as primary key since it is necessary for an Entity in Spring
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//Using accountNumber as a foreign key to avoid invalid account balance record
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
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
}

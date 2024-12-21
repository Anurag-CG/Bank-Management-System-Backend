package com.example.adminservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//Entity to handle the banker login
public class BankerLogin {
	@Id
	//Variable to store the Banker Id
	private String bankerId;
	//Variable to store Banker's Name
	private String bankerName;
	//Variable to store Banker's Password
	private String password;
}
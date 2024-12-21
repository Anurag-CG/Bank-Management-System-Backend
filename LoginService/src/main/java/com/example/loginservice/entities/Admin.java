package com.example.loginservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//Entity format to store the admin login credentials
public class Admin {

	@Id
	//Variable to store adminId
	private String adminId;
	//Variable to store admin name
	private String adminName;
	//Variable to store admin password
	private String password;
}

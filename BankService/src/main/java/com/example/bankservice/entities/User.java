package com.example.bankservice.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
//Format to store User details
public class User {
	
	@Id
	//Variable to store accountNumber being generated randomly between described range
	private Long accountNumber;
	//variable to store aadhar number of user
	@Column(unique=true)
	private Long aadharNumber;
	//Variable to store PAN number of user
	@Column(unique=true)
	private String panNumber;
	//Variable to store first name of user
	private String firstName;
	//Variable to store last name of user
	private String lastName;
	//Variable to store Father's name of user
	private String fname;
	//Variable to store Mother's name of user
	private String mname;
	//Variable to store Date of Birth in format (yyyy-MM-dd)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	//Variable to store qualification selected from dropdown menu
	private String qualification;
	//Variable to store address of the user
	private String address;
	//Variable to store the city of the user
	private String city;
	//Variable to store the state selected from dropdown menu
	private String state;
	//Variable to store the PinCode
	private int pincode;
}

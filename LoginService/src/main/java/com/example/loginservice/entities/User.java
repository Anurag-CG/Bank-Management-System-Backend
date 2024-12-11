package com.example.loginservice.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class User {
	
	@Id
	private Long accountNumber;
	private Long aadharNumber;
	private String panNumber;
	private String firstName;
	private String lastName;
	private String fname;
	private String mname;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	private String qualification;
	private String address;
	private String city;
	private String state;
	private int pincode;
}

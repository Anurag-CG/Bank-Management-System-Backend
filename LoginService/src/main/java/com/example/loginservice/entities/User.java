package com.example.loginservice.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public User(Long accountNumber, Long aadharNumber, String panNumber, String firstName, String lastName,
			String fname, String mname, Date dateOfBirth, String qualification, String address, String city,
			String state, int pincode) {
		super();
		this.accountNumber = accountNumber;
		this.aadharNumber = aadharNumber;
		this.panNumber = panNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fname = fname;
		this.mname = mname;
		this.dateOfBirth = dateOfBirth;
		this.qualification = qualification;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

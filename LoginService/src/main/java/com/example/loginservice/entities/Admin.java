package com.example.loginservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//Entity format to store the admin login credentials
public class Admin {

	@Id
	//Variable to store adminId
	private String adminId;
	//Variable to store admin name
	private String adminName;
	//Variable to store admin password
	private String password;
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Admin(String adminId, String adminName, String password) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.password = password;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

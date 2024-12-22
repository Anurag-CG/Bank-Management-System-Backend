package com.example.loginservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class BankerLogin {
	@Id
	private String bankerId;
	private String bankerName;
	private String password;
	public String getBankerId() {
		return bankerId;
	}
	public void setBankerId(String bankerId) {
		this.bankerId = bankerId;
	}
	public String getBankerName() {
		return bankerName;
	}
	public void setBankerName(String bankerName) {
		this.bankerName = bankerName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public BankerLogin(String bankerId, String bankerName, String password) {
		super();
		this.bankerId = bankerId;
		this.bankerName = bankerName;
		this.password = password;
	}
	public BankerLogin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

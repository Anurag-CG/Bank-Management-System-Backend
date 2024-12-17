package com.example.bankservice.entities;

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
	private String bankerId;
	private String bankerName;
	private String password;
}
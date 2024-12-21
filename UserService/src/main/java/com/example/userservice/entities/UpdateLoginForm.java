package com.example.userservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//Format to store data in a sequence to update Password
public class UpdateLoginForm {
	//Variable to store the userId
	private String userId;
	//Variable to store the current password
	private String previousPassword;
	//Variable to store the updated password
	private String newPassword;
}

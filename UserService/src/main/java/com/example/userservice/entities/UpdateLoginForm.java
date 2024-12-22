package com.example.userservice.entities;


//Format to store data in a sequence to update Password
public class UpdateLoginForm {
	//Variable to store the userId
	private String userId;
	//Variable to store the current password
	private String previousPassword;
	//Variable to store the updated password
	private String newPassword;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPreviousPassword() {
		return previousPassword;
	}
	public void setPreviousPassword(String previousPassword) {
		this.previousPassword = previousPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public UpdateLoginForm(String userId, String previousPassword, String newPassword) {
		super();
		this.userId = userId;
		this.previousPassword = previousPassword;
		this.newPassword = newPassword;
	}
	public UpdateLoginForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

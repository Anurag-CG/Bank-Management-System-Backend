package com.example.userservice.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//Format to store data in a sequence for Fund Transfer from one account to another
public class FundTransfer {
	//Variable storing sender's accountNumber;
	private Long senderAccount;
	//Variable storing receiver's accountNumber;
	private Long receiverAccount;
	//Variable storing amount to be sent
	private Long balance;
}

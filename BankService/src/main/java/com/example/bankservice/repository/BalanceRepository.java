package com.example.bankservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankservice.entities.Funds;
import com.example.bankservice.entities.User;

//Creating table in the provided database to store the balance information of users
@Repository
public interface BalanceRepository extends JpaRepository<Funds, Long>{

	//Method to search for the user through it's information
	Optional<Funds> findByUser(User senderAccount);

}

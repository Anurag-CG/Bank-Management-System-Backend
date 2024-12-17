package com.example.bankservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankservice.entities.User;

//Creating table in the provided database to store the information of users
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	//Method to check if aadhar number is already present in the record
	boolean existsByPanNumber(String panNumber);
	//Method to check if Pan number is already present in the record
	boolean existsByAadharNumber(Long aadharNumber);
}

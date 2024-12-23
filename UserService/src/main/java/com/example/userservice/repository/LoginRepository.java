package com.example.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.userservice.entities.Login;
import com.example.userservice.entities.User;

//Creating table in the provided database to store the login information of users
@Repository
public interface LoginRepository extends JpaRepository<Login, String>{

	//Method to search Login information by User information
	Login findByUser(User user);

}

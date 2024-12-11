package com.example.loginservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.loginservice.entities.Login;
import com.example.loginservice.entities.User;

@Repository
public interface LoginRepository extends JpaRepository<Login, String>{

	Login findByUser(User user);

}
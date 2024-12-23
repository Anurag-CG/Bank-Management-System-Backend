package com.example.loginservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.loginservice.entities.User;

@Repository
//Repository to store User details
public interface UserRepository extends JpaRepository<User, Long>{

	boolean existsByPanNumber(String panNumber);

	boolean existsByAadharNumber(Long aadharNumber);
}

package com.example.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.adminservice.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	boolean existsByPanNumber(String panNumber);

	boolean existsByAadharNumber(Long aadharNumber);
}
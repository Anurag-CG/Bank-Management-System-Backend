package com.example.loginservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.loginservice.entities.Admin;

@Repository
//Repository to store Admin login information
public interface AdminRepository extends JpaRepository<Admin, String>{

}

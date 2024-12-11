package com.example.loginservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.loginservice.entities.BankerLogin;

@Repository
public interface BankerRepository extends JpaRepository<BankerLogin, Long>{

}

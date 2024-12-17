package com.example.bankservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankservice.entities.BankerLogin;


@Repository
public interface BankerRepository extends JpaRepository<BankerLogin, String>{

}

package com.example.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.adminservice.entities.BankerLogin;


@Repository
public interface BankerRepository extends JpaRepository<BankerLogin, String>{

}

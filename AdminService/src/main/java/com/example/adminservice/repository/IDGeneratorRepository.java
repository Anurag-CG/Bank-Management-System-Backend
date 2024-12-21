package com.example.adminservice.repository;

import com.example.adminservice.entities.IDGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IDGeneratorRepository extends JpaRepository<IDGenerator, LocalDate> {

    Optional<IDGenerator> findByLastGeneratedDay(LocalDate date);
}

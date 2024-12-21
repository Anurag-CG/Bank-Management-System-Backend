package com.example.adminservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
//Entity for Id Generation of Banker
public class IDGenerator {

    @Id
    private LocalDate lastGeneratedDay;
    private int counter;
}

package com.example.adminservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity

//Entity for Id Generation of Banker
public class IDGenerator {

    @Id
    private LocalDate lastGeneratedDay;
    private int counter;
	public LocalDate getLastGeneratedDay() {
		return lastGeneratedDay;
	}
	public void setLastGeneratedDay(LocalDate lastGeneratedDay) {
		this.lastGeneratedDay = lastGeneratedDay;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
    
    
}

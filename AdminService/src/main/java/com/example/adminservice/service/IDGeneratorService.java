package com.example.adminservice.service;

import com.example.adminservice.entities.IDGenerator;
import com.example.adminservice.repository.IDGeneratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class IDGeneratorService {

	@Autowired
	//Repository to handle DB operations for IDGenerator
	private IDGeneratorRepository idGeneratorRepository;

	//Date format for generating ID
	private static final String DATE_FORMAT = "yyMMdd";

	public String generateID() {
		//Get the current date
		LocalDate currentDate = LocalDate.now();

		//Generate the date part of the ID
		String datePart = getDatePart(currentDate);

		//Fetch the IDGenerator record for the current day (if it exists)
		Optional<IDGenerator> idGeneratorOpt = idGeneratorRepository.findByLastGeneratedDay(currentDate);

		//Default counter value for the first ID of the day
		int counter = 1;

		if (idGeneratorOpt.isPresent()) {
			//If a record exists, retrieve and increment the counter
			IDGenerator idGenerator = idGeneratorOpt.get();
			//Get the current counter
			counter = idGenerator.getCounter();
			//Increment the counter for the next ID
			counter++;
			//Update the counter in the DB
			idGenerator.setCounter(counter);
			idGeneratorRepository.save(idGenerator);
		} else {
			//If no record exists, create a new one with a counter of 1
			IDGenerator newIdGenerator = new IDGenerator();
			newIdGenerator.setLastGeneratedDay(currentDate);
			newIdGenerator.setCounter(counter);
			//Save the new record
			idGeneratorRepository.save(newIdGenerator);
		}
		//Format the counter as a 3-digit number
		String counterPart = String.format("%03d", counter);
		//Return the final ID in the format "YYMMDD###"
		return datePart + counterPart;
	}

	private String getDatePart(LocalDate currentDate) {
		//Convert the LocalDate to the required "yyMMdd" format
		return currentDate.format(java.time.format.DateTimeFormatter.ofPattern(DATE_FORMAT));
	}
}

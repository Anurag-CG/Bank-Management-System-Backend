package com.example.adminservice.service;

import com.example.adminservice.entities.IDGenerator;
import com.example.adminservice.repository.IDGeneratorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IDGeneratorServiceTest {

    @Mock
    private IDGeneratorRepository idGeneratorRepository;

    @InjectMocks
    private IDGeneratorService idGeneratorService;

    private LocalDate currentDate;

    @BeforeEach
    public void setup() {
        // Set a fixed date for consistent test results
        currentDate = LocalDate.of(2024, 12, 21);
    }

    // Test case for generating a new ID when no record exists
    @Test
    void testGenerateID_NewDay() {
        // Arrange: Mock the repository to return empty for the current date
        when(idGeneratorRepository.findByLastGeneratedDay(currentDate)).thenReturn(Optional.empty());

        // Act: Call the generateID method
        String generatedID = idGeneratorService.generateID();

        // Assert: The generated ID should start with the correct date and have a 3-digit counter
        assertTrue(generatedID.startsWith(currentDate.format(java.time.format.DateTimeFormatter.ofPattern("yyMMdd"))), "ID should start with the date");
        assertTrue(generatedID.length() == 6 + 3, "ID should be in the format yyMMdd###");
        assertTrue(generatedID.matches("\\d{6}\\d{3}"), "ID should match the pattern yyMMdd###");
    }

    // Test case for generating an ID when a record already exists for the current day
    @Test
    void testGenerateID_ExistingDay() {
        // Arrange: Create a mock IDGenerator record with a counter of 5 for the current date
        IDGenerator existingRecord = new IDGenerator();
        existingRecord.setLastGeneratedDay(currentDate);
        existingRecord.setCounter(5);

        // Mock the repository to return this record for the current date
        when(idGeneratorRepository.findByLastGeneratedDay(currentDate)).thenReturn(Optional.of(existingRecord));

        // Mock saving the updated IDGenerator record and return the updated entity
        when(idGeneratorRepository.save(existingRecord)).thenReturn(existingRecord);

        // Act: Call the generateID method
        String generatedID = idGeneratorService.generateID();

        // Assert: The generated ID should increment the counter and start with the correct date
        assertTrue(generatedID.startsWith(currentDate.format(java.time.format.DateTimeFormatter.ofPattern("yyMMdd"))), "ID should start with the date");
        assertTrue(generatedID.length() == 6 + 3, "ID should be in the format yyMMdd###");
        assertTrue(generatedID.matches("\\d{6}\\d{3}"), "ID should match the pattern yyMMdd###");

        // Verify that the counter was incremented
        assertEquals(6, existingRecord.getCounter(), "Counter should have been incremented");
    }
}

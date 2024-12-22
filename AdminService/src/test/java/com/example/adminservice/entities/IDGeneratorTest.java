package com.example.adminservice.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class IDGeneratorTest {

    private IDGenerator idGenerator;

    // Initialize the IDGenerator object before each test
    @BeforeEach
    void setUp() {
        idGenerator = new IDGenerator();
    }

    // Success Test: Check if the setter and getter for lastGeneratedDay work correctly
    @Test
    void testGetSetLastGeneratedDay_Success() {
        // Set and get the lastGeneratedDay value
        LocalDate date = LocalDate.of(2024, 12, 21);
        idGenerator.setLastGeneratedDay(date);
        assertEquals(date, idGenerator.getLastGeneratedDay(), "The last generated day should be '2024-12-21'");
    }

    // Failure Test: Try setting null for lastGeneratedDay and ensure the behavior is correct
    @Test
    void testSetNullLastGeneratedDay_Failure() {
        // Set null for lastGeneratedDay and check for handling of null values
        idGenerator.setLastGeneratedDay(null);
        assertNull(idGenerator.getLastGeneratedDay(), "Last generated day should be null");
    }

    // Success Test: Check if the setter and getter for counter work correctly
    @Test
    void testGetSetCounter_Success() {
        // Set and get the counter value
        idGenerator.setCounter(5);
        assertEquals(5, idGenerator.getCounter(), "The counter value should be 5");
    }

    // Failure Test: Try setting a negative counter value and check how it behaves
    @Test
    void testSetNegativeCounter_Failure() {
        // Set a negative counter value
        idGenerator.setCounter(-5);
        assertEquals(-5, idGenerator.getCounter(), "The counter value should be set to -5, but validation could be added to prevent this");
    }

    // Success Test: Check if setting and getting a different lastGeneratedDay works
    @Test
    void testSetDifferentLastGeneratedDay_Success() {
        // Set and check if a different date is correctly set
        LocalDate newDate = LocalDate.of(2024, 12, 22);
        idGenerator.setLastGeneratedDay(newDate);
        assertEquals(newDate, idGenerator.getLastGeneratedDay(), "The last generated day should be '2024-12-22'");
    }

    // Success Test: Check if setting and getting a different counter value works
    @Test
    void testSetDifferentCounter_Success() {
        // Set and check if a different counter value is correctly set
        idGenerator.setCounter(10);
        assertEquals(10, idGenerator.getCounter(), "The counter value should be 10");
    }

    // Failure Test: Test for invalid counter value (0 or negative), and validate the behavior
    @Test
    void testSetInvalidCounter_Failure() {
        // Set counter to zero (if zero is considered invalid)
        idGenerator.setCounter(0);
        assertEquals(0, idGenerator.getCounter(), "The counter value should be set to 0. Validation could be added to restrict 0.");

        // Set counter to negative value
        idGenerator.setCounter(-10);
        assertEquals(-10, idGenerator.getCounter(), "The counter value should allow negative values, but validation could prevent this.");
    }
}

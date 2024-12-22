package com.example.bankservice.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

class FundsTest {

    private Funds funds;
    private User mockUser;

    @BeforeEach
    public void setUp() {
        // Create a mock User to use for Funds association
        mockUser = Mockito.mock(User.class);
        
        // Create a new Funds object with mock user and sample balance
        funds = new Funds(mockUser, 1000L);
    }

    // Success Scenario: Test constructor with valid parameters
    @Test
    public void testConstructor_Success() {
        // Check that the Funds object was created with the correct values
        assertEquals(mockUser, funds.getUser(), "User should match the mock user");
        assertEquals(1000L, funds.getBalance(), "Balance should be 1000");
    }

    // Failure Scenario: Test constructor with null values (if applicable)
    @Test
    public void testConstructor_Failure() {
        Funds fundsWithNull = new Funds(null, null);
        
        // Check that user and balance are null
        assertNull(fundsWithNull.getUser(), "User should be null");
        assertNull(fundsWithNull.getBalance(), "Balance should be null");
    }

    // Success Scenario: Test setter and getter for user
    @Test
    public void testUserSetterGetter_Success() {
        User newUser = Mockito.mock(User.class); // Mock another user
        funds.setUser(newUser);

        // Check that the user is updated correctly
        assertEquals(newUser, funds.getUser(), "User should be updated to the new user");
    }

    // Failure Scenario: Test setter with null user
    @Test
    public void testUserSetterWithNull_Failure() {
        funds.setUser(null);
        
        // Check that the user is set to null
        assertNull(funds.getUser(), "User should be null after setting to null");
    }

    // Success Scenario: Test setter and getter for balance
    @Test
    public void testBalanceSetterGetter_Success() {
        funds.setBalance(2000L);

        // Assert that the balance is updated correctly
        assertEquals(2000L, funds.getBalance(), "Balance should be updated to 2000");
    }

    // Failure Scenario: Test setter with invalid balance (e.g., negative value)
    @Test
    public void testBalanceSetterWithInvalidValue_Failure() {
        funds.setBalance(-500L);

        // Assert that the balance is set to the invalid negative value
        assertEquals(-500L, funds.getBalance(), "Balance should be set to -500 even if it's invalid");
    }

    // Success Scenario: Test default constructor (should initialize null values)
    @Test
    public void testDefaultConstructor_Success() {
        Funds defaultFunds = new Funds();
        
        // Check that the default Funds object has null user and balance
        assertNull(defaultFunds.getUser(), "User should be null in default constructor");
        assertNull(defaultFunds.getBalance(), "Balance should be null in default constructor");
    }
}


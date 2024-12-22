package com.example.userservice.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FundsTest {

    private Funds funds;
    private User user;

    @BeforeEach
    void setUp() {
        // Set up a mock User object for the test
        user = new User(); 
        user.setAccountNumber(123456789L);

        // Create a Funds object
        funds = new Funds(user, 1000L);
    }

    // Test for constructor success (valid initialization)
    @Test
    void testFundsConstructor_Success() {
        assertNotNull(funds, "Funds object should be initialized");
        assertEquals(user, funds.getUser(), "User should be set correctly in Funds");
        assertEquals(1000L, funds.getBalance(), "Balance should be initialized correctly");
    }

    // Test for constructor failure (null user and balance should be null)
    @Test
    void testFundsConstructor_Failure() {
        Funds invalidFunds = new Funds(null, null);
        assertNull(invalidFunds.getUser(), "User should be null");
        assertNull(invalidFunds.getBalance(), "Balance should be null");
    }

    // Test for setter and getter for balance - success case
    @Test
    void testSetBalance_Success() {
        funds.setBalance(2000L);
        assertEquals(2000L, funds.getBalance(), "Balance should be updated to 2000");
    }

    // Test for setter and getter for balance - failure case
    @Test
    void testSetBalance_Failure() {
        funds.setBalance(500L);
        assertNotEquals(2000L, funds.getBalance(), "Balance should not be equal to 2000 after setting to 500");
    }

    // Test for setter and getter for user - success case
    @Test
    void testSetUser_Success() {
        User newUser = new User();
        newUser.setAccountNumber(987654321L);
        funds.setUser(newUser);
        assertEquals(newUser, funds.getUser(), "User should be updated correctly");
    }

    // Test for setter and getter for user - failure case
    @Test
    void testSetUser_Failure() {
        User invalidUser = new User();
        invalidUser.setAccountNumber(999999999L);
        funds.setUser(invalidUser);
        assertNotEquals(user, funds.getUser(), "User should not be equal to the original user after setting new one");
    }

    // Test the default constructor - success case
    @Test
    void testDefaultConstructor_Success() {
        Funds defaultFunds = new Funds();
        assertNull(defaultFunds.getUser(), "User should be null by default");
        assertNull(defaultFunds.getBalance(), "Balance should be null by default");
    }

    // Test the default constructor - failure case
    @Test
    void testDefaultConstructor_Failure() {
        Funds defaultFunds = new Funds();
        assertNotEquals(user, defaultFunds.getUser(), "User should not be equal to a previously set user");
        assertNotEquals(1000L, defaultFunds.getBalance(), "Balance should not be equal to 1000 by default");
    }
}

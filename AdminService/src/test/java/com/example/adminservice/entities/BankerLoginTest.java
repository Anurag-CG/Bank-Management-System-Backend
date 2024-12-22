package com.example.adminservice.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankerLoginTest {

    private BankerLogin bankerLogin;

    //To Initialize a fresh BankerLogin object before each test execution
    @BeforeEach
    void setUp() {

        bankerLogin = new BankerLogin("B123", "Aman Kumar", "password123");
    }

    
    //Test the getter for bankerId to check if it returns the correct value.
    @Test
    void testGetBankerId_Success() {
        // Assert that the bankerId is correctly retrieved
        assertEquals("B123", bankerLogin.getBankerId(), "Banker ID should be 'B123'");
    }

    // Test the setter for bankerId to check if it properly sets a new value.
    @Test
    void testSetBankerId_Success() {
        // Set a new bankerId and check if it updates correctly
        bankerLogin.setBankerId("B456");
        assertEquals("B456", bankerLogin.getBankerId(), "Banker ID should be updated to 'B456'");
    }

    // Test the getter for bankerName to check if it returns the correct value.
    @Test
    void testGetBankerName_Success() {
        // Assert that the bankerName is correctly retrieved
        assertEquals("Aman Kumar", bankerLogin.getBankerName(), "Banker name should be 'Aman Kumar'");
    }

    // Test the setter for bankerName to check if it properly sets a new value.
    @Test
    void testSetBankerName_Success() {
        // Set a new bankerName and verify if it updates correctly
        bankerLogin.setBankerName("Shivam Singh");
        assertEquals("Shivam Singh", bankerLogin.getBankerName(), "Banker name should be updated to 'Shivam Singh'");
    }

    // Test the getter for password to check if it returns the correct value.
    @Test
    void testGetPassword_Success() {
        // Assert that the password is correctly retrieved
        assertEquals("password123", bankerLogin.getPassword(), "Password should be 'password123'");
    }

    // Test the setter for password to check if it properly sets a new value.
    @Test
    void testSetPassword_Success() {
        // Set a new password and verify if it updates correctly
        bankerLogin.setPassword("newpassword456");
        assertEquals("newpassword456", bankerLogin.getPassword(), "Password should be updated to 'newpassword456'");
    }

    // Test the constructor with parameters to check if values are correctly assigned.
    @Test
    void testBankerLoginConstructor_Success() {
        // Create a new BankerLogin object using the constructor
        BankerLogin newBankerLogin = new BankerLogin("B789", "Anurag Kumar", "anurag123");
        
        // Assert that the constructor initializes fields correctly
        assertEquals("B789", newBankerLogin.getBankerId(), "Banker ID should be 'B789'");
        assertEquals("Anurag Kumar", newBankerLogin.getBankerName(), "Banker name should be 'Anurag Kumar'");
        assertEquals("anurag123", newBankerLogin.getPassword(), "Password should be 'anurag123'");
    }

    // Test the default constructor to check if fields are initialized as null.
    @Test
    void testDefaultConstructor_Success() {
        // Create a BankerLogin object using the default constructor
        BankerLogin defaultBankerLogin = new BankerLogin();
        
        // Assert that all fields are null when the default constructor is used
        assertNull(defaultBankerLogin.getBankerId(), "Banker ID should be null");
        assertNull(defaultBankerLogin.getBankerName(), "Banker name should be null");
        assertNull(defaultBankerLogin.getPassword(), "Password should be null");
    }

    // Failure Test Cases --------------------------------------------------------------

    // Test the setter for bankerId to check invalid value handling (failure scenario).
    @Test
    void testSetInvalidBankerId_Failure() {
        // Set an invalid bankerId (empty string)
        bankerLogin.setBankerId(""); 
        
        // Assert that the bankerId is set as empty string
        assertEquals("", bankerLogin.getBankerId(), "Banker ID should be empty");
    }

    // Test the setter for password with an invalid password (null).
    @Test
    void testSetNullPassword_Failure() {
        // Try setting the password as null and check for unexpected results
        bankerLogin.setPassword(null);
        
        // Assert that the password is now null
        assertNull(bankerLogin.getPassword(), "Password should be null after setting to null");
    }

    // Test the setter for bankerName with an invalid value (null).
    @Test
    void testSetNullBankerName_Failure() {
        // Set the bankerName to null
        bankerLogin.setBankerName(null);

        // Assert that the bankerName is now null
        assertNull(bankerLogin.getBankerName(), "Banker name should be null after setting to null");
    }

    // Test setter and getter with invalid values and ensure no unintended behavior occurs.
    @Test
    void testInvalidValues() {
        // Test setting invalid values like empty strings or null
        bankerLogin.setBankerId(null);
        bankerLogin.setBankerName("");
        bankerLogin.setPassword("");

        // Assert that invalid inputs are handled(should be set as null or empty)
        assertNull(bankerLogin.getBankerId(), "Banker ID should be null after setting it to null");
        assertEquals("", bankerLogin.getBankerName(), "Banker name should be empty after setting it to an empty string");
        assertEquals("", bankerLogin.getPassword(), "Password should be empty after setting it to an empty string");
    }
}

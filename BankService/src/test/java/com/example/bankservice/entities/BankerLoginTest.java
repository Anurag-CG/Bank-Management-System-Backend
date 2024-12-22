package com.example.bankservice.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankerLoginTest {

    private BankerLogin bankerLogin;

    @BeforeEach
    public void setUp() {
        // Initialize the BankerLogin object before each test
        bankerLogin = new BankerLogin();
    }

    // Success Scenario: Constructor with valid parameters
    @Test
    public void testConstructorWithParameters_Success() {
        // Create a new BankerLogin object with valid values
        BankerLogin banker = new BankerLogin("B001", "Aman Kumar", "password123");

        // Assert that the values are correctly set
        assertEquals("B001", banker.getBankerId(), "Banker ID should be B001");
        assertEquals("Aman Kumar", banker.getBankerName(), "Banker's name should be Aman Kumar");
        assertEquals("password123", banker.getPassword(), "Banker's password should be password123");
    }

    // Failure Scenario: Constructor with invalid parameters (null values)
    @Test
    public void testConstructorWithParameters_Failure() {
        // Try creating a BankerLogin object with null values
        BankerLogin banker = new BankerLogin(null, null, null);

        // Assert that the values are null (no validations added, as per current entity design)
        assertNull(banker.getBankerId(), "Banker ID should be null");
        assertNull(banker.getBankerName(), "Banker's name should be null");
        assertNull(banker.getPassword(), "Password should be null");
    }

    // Success Scenario: Test setters and getters with valid values
    @Test
    public void testDefaultConstructorAndSetters_Success() {
        // Use setters to set valid values
        bankerLogin.setBankerId("B002");
        bankerLogin.setBankerName("Shivam Kumar");
        bankerLogin.setPassword("password456");

        // Assert that the values are correctly set
        assertEquals("B002", bankerLogin.getBankerId(), "Banker ID should be B002");
        assertEquals("Shivam Kumar", bankerLogin.getBankerName(), "Banker's name should be Shivam Kumar");
        assertEquals("password456", bankerLogin.getPassword(), "Banker's password should be password456");
    }

    // Failure Scenario: Test setters with null values
    @Test
    public void testSettersWithNullValues_Failure() {
        // Set null values using setters
        bankerLogin.setBankerId(null);
        bankerLogin.setBankerName(null);
        bankerLogin.setPassword(null);

        // Assert that the values are set to null
        assertNull(bankerLogin.getBankerId(), "Banker ID should be null");
        assertNull(bankerLogin.getBankerName(), "Banker's name should be null");
        assertNull(bankerLogin.getPassword(), "Password should be null");
    }

    // Success Scenario: Test getter and setter for bankerId
    @Test
    public void testBankerIdGetterSetter_Success() {
        bankerLogin.setBankerId("B003");

        // Assert that the setter works correctly
        assertEquals("B003", bankerLogin.getBankerId(), "Banker ID should be B003");
    }

    // Failure Scenario: Test getter and setter with empty bankerId
    @Test
    public void testBankerIdSetterWithEmptyValue_Failure() {
        bankerLogin.setBankerId("");

        // Assert that the value is set as empty string (since no validation exists in the entity)
        assertEquals("", bankerLogin.getBankerId(), "Banker ID should be an empty string");
    }

    // Success Scenario: Test setter and getter for bankerName
    @Test
    public void testBankerNameGetterSetter_Success() {
        bankerLogin.setBankerName("Anurag Kumar");

        // Assert that the setter works correctly
        assertEquals("Anurag Kumar", bankerLogin.getBankerName(), "Banker's name should be Anurag Kumar");
    }

    // Failure Scenario: Test setter and getter with null bankerName
    @Test
    public void testBankerNameSetterWithNullValue_Failure() {
        bankerLogin.setBankerName(null);

        // Assert that the value is set to null
        assertNull(bankerLogin.getBankerName(), "Banker's name should be null");
    }

    // Success Scenario: Test setter and getter for password
    @Test
    public void testPasswordGetterSetter_Success() {
        bankerLogin.setPassword("securePassword123");

        // Assert that the setter works correctly
        assertEquals("securePassword123", bankerLogin.getPassword(), "Password should be securePassword123");
    }

    // Failure Scenario: Test setter and getter with empty password
    @Test
    public void testPasswordSetterWithEmptyValue_Failure() {
        bankerLogin.setPassword("");

        // Assert that the value is set to empty string
        assertEquals("", bankerLogin.getPassword(), "Password should be an empty string");
    }

    // Success Scenario: Test equality of two BankerLogin objects
    @Test
    public void testEquals_Success() {
        BankerLogin banker1 = new BankerLogin("B004", "Rohit Kumar", "password789");
        BankerLogin banker2 = new BankerLogin("B004", "Rohit Kumar", "password789");

        // Assert that both objects are equal because they have the same values
        assertEquals(banker1.getBankerId(), banker2.getBankerId(), "Banker IDs should be equal");
        assertEquals(banker1.getBankerName(), banker2.getBankerName(), "Banker's names should be equal");
        assertEquals(banker1.getPassword(), banker2.getPassword(), "Passwords should be equal");
    }

    // Failure Scenario: Test inequality of two BankerLogin objects
    @Test
    public void testEquals_Failure() {
        BankerLogin banker1 = new BankerLogin("B004", "Rohit Kumar", "password789");
        BankerLogin banker2 = new BankerLogin("B005", "Nikhil", "password123");

        // Assert that the two objects are not equal because the values are different
        assertNotEquals(banker1.getBankerId(), banker2.getBankerId(), "Banker IDs should not be equal");
        assertNotEquals(banker1.getBankerName(), banker2.getBankerName(), "Banker's names should not be equal");
        assertNotEquals(banker1.getPassword(), banker2.getPassword(), "Passwords should not be equal");
    }

    // Success Scenario: Test setter changes values correctly
    @Test
    public void testSetterChangesValue_Success() {
        bankerLogin.setBankerId("B005");
        bankerLogin.setBankerName("Nikhil");
        bankerLogin.setPassword("newPassword");

        // Now change the values using setters again
        bankerLogin.setBankerId("B006");
        bankerLogin.setBankerName("Shivam Kumar");
        bankerLogin.setPassword("updatedPassword");

        // Assert that the values are updated correctly
        assertEquals("B006", bankerLogin.getBankerId(), "Banker ID should be updated to B006");
        assertEquals("Shivam Kumar", bankerLogin.getBankerName(), "Banker's name should be updated to Shivam Kumar");
        assertEquals("updatedPassword", bankerLogin.getPassword(), "Password should be updated to updatedPassword");
    }

    // Success Scenario: Test initial state (null values initially)
    @Test
    public void testInitialState_Success() {
        // Assert that the object is created with default null values
        assertNull(bankerLogin.getBankerId(), "Banker ID should be null initially");
        assertNull(bankerLogin.getBankerName(), "Banker's name should be null initially");
        assertNull(bankerLogin.getPassword(), "Password should be null initially");
    }
}

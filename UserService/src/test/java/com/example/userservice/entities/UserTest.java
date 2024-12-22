package com.example.userservice.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        // Set up User object with parameterized constructor
        user = new User(123456L, 9876543210L, "ABCDE1234F", "Aman", "Kumar", "Shivam", "Rohit",
                new Date(), "Bachelor", "123 Main St", "Patna", "Bihar", 800001);
    }

    // Test for constructor success
    @Test
    void testUserConstructor_Success() {
        assertNotNull(user, "User object should be initialized");
        assertEquals(123456L, user.getAccountNumber(), "Account number should be correctly initialized");
        assertEquals(9876543210L, user.getAadharNumber(), "Aadhar number should be correctly initialized");
        assertEquals("ABCDE1234F", user.getPanNumber(), "PAN number should be correctly initialized");
        assertEquals("Aman", user.getFirstName(), "First name should be correctly initialized");
        assertEquals("Kumar", user.getLastName(), "Last name should be correctly initialized");
        assertEquals("Shivam", user.getFname(), "Father's name should be correctly initialized");
        assertEquals("Rohit", user.getMname(), "Mother's name should be correctly initialized");
        assertNotNull(user.getDateOfBirth(), "Date of birth should not be null");
        assertEquals("Bachelor", user.getQualification(), "Qualification should be correctly initialized");
        assertEquals("123 Main St", user.getAddress(), "Address should be correctly initialized");
        assertEquals("Patna", user.getCity(), "City should be correctly initialized");
        assertEquals("Bihar", user.getState(), "State should be correctly initialized");
        assertEquals(800001, user.getPincode(), "Pincode should be correctly initialized");
    }

    // Test for default constructor (null or default values)
    @Test
    void testUserDefaultConstructor_Success() {
        User defaultUser = new User();
        assertNull(defaultUser.getAccountNumber(), "Account number should be null by default");
        assertNull(defaultUser.getAadharNumber(), "Aadhar number should be null by default");
        assertNull(defaultUser.getPanNumber(), "PAN number should be null by default");
        assertNull(defaultUser.getFirstName(), "First name should be null by default");
        assertNull(defaultUser.getLastName(), "Last name should be null by default");
        assertNull(defaultUser.getFname(), "Father's name should be null by default");
        assertNull(defaultUser.getMname(), "Mother's name should be null by default");
        assertNull(defaultUser.getDateOfBirth(), "Date of birth should be null by default");
        assertNull(defaultUser.getQualification(), "Qualification should be null by default");
        assertNull(defaultUser.getAddress(), "Address should be null by default");
        assertNull(defaultUser.getCity(), "City should be null by default");
        assertNull(defaultUser.getState(), "State should be null by default");
        assertEquals(0, defaultUser.getPincode(), "Pincode should be 0 by default");
    }

    // Test for setter and getter of accountNumber
    @Test
    void testSetAccountNumber_Success() {
        user.setAccountNumber(654321L);
        assertEquals(654321L, user.getAccountNumber(), "Account number should be updated correctly");
    }

    // Test for setter and getter of aadharNumber
    @Test
    void testSetAadharNumber_Success() {
        user.setAadharNumber(1234567890L);
        assertEquals(1234567890L, user.getAadharNumber(), "Aadhar number should be updated correctly");
    }

    // Test for setter and getter of panNumber
    @Test
    void testSetPanNumber_Success() {
        user.setPanNumber("XYZ9876543");
        assertEquals("XYZ9876543", user.getPanNumber(), "PAN number should be updated correctly");
    }

    // Test for setter and getter of firstName
    @Test
    void testSetFirstName_Success() {
        user.setFirstName("Anurag");
        assertEquals("Anurag", user.getFirstName(), "First name should be updated correctly");
    }

    // Test for setter and getter of lastName
    @Test
    void testSetLastName_Success() {
        user.setLastName("Kumar");
        assertEquals("Kumar", user.getLastName(), "Last name should be updated correctly");
    }

    // Test for setter and getter of fname
    @Test
    void testSetFname_Success() {
        user.setFname("Shivam");
        assertEquals("Shivam", user.getFname(), "Father's name should be updated correctly");
    }

    // Test for setter and getter of mname
    @Test
    void testSetMname_Success() {
        user.setMname("Rohit");
        assertEquals("Rohit", user.getMname(), "Mother's name should be updated correctly");
    }

    // Test for setter and getter of dateOfBirth
    @Test
    void testSetDateOfBirth_Success() {
        Date newDate = new Date(2025, 1, 1);
        user.setDateOfBirth(newDate);
        assertEquals(newDate, user.getDateOfBirth(), "Date of birth should be updated correctly");
    }

    // Test for setter and getter of qualification
    @Test
    void testSetQualification_Success() {
        user.setQualification("Master's");
        assertEquals("Master's", user.getQualification(), "Qualification should be updated correctly");
    }

    // Test for setter and getter of address
    @Test
    void testSetAddress_Success() {
        user.setAddress("456 Secondary St");
        assertEquals("456 Secondary St", user.getAddress(), "Address should be updated correctly");
    }

    // Test for setter and getter of city
    @Test
    void testSetCity_Success() {
        user.setCity("New Delhi");
        assertEquals("New Delhi", user.getCity(), "City should be updated correctly");
    }

    // Test for setter and getter of state
    @Test
    void testSetState_Success() {
        user.setState("Delhi");
        assertEquals("Delhi", user.getState(), "State should be updated correctly");
    }

    // Test for setter and getter of pincode
    @Test
    void testSetPincode_Success() {
        user.setPincode(110001);
        assertEquals(110001, user.getPincode(), "Pincode should be updated correctly");
    }
}

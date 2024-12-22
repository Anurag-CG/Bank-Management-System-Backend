package com.example.bankservice.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        // Create a sample Date object for testing (e.g., using the current date)
        Date dateOfBirth = new Date();

        // Create the User object using the constructor
        user = new User(
                123456789L, // accountNumber
                9876543210L, // aadharNumber
                "ABC1234D", // panNumber
                "Aman", // firstName
                "Kumar", // lastName
                "Shivam", // fname (Father's name)
                "Anurag", // mname (Mother's name)
                dateOfBirth, // dateOfBirth
                "Bachelors", // qualification
                "123 Street", // address
                "CityName", // city
                "StateName", // state
                123456 // pincode
        );
    }

    // **Success Scenario**: Test the constructor and getter methods for a User object
    @Test
    public void testUser_Success() {
        assertNotNull(user, "User object should not be null");
        assertEquals(123456789L, user.getAccountNumber(), "Account number should match");
        assertEquals(9876543210L, user.getAadharNumber(), "Aadhar number should match");
        assertEquals("ABC1234D", user.getPanNumber(), "PAN number should match");
        assertEquals("Aman", user.getFirstName(), "First name should match");
        assertEquals("Kumar", user.getLastName(), "Last name should match");
        assertEquals("Shivam", user.getFname(), "Father's name should match");
        assertEquals("Anurag", user.getMname(), "Mother's name should match");
        assertEquals("Bachelors", user.getQualification(), "Qualification should match");
        assertEquals("123 Street", user.getAddress(), "Address should match");
        assertEquals("CityName", user.getCity(), "City should match");
        assertEquals("StateName", user.getState(), "State should match");
        assertEquals(123456, user.getPincode(), "Pincode should match");

        // Check that the date is not null (in real tests, you might compare the actual date)
        assertNotNull(user.getDateOfBirth(), "Date of birth should not be null");
    }

    // **Failure Scenario**: Test when user object is not created or when some values are incorrect
    @Test
    public void testUser_Failure() {
        // Creating a User object with incorrect data
        User incorrectUser = new User(
                123456789L, // accountNumber (same as correct one)
                1112223333L, // aadharNumber (incorrect)
                "DEF5678G", // panNumber (incorrect)
                "Rohit", // firstName (incorrect)
                "Kumar", // lastName (same as correct)
                "Aman", // fname (incorrect)
                "Shivam", // mname (correct)
                new Date(), // dateOfBirth (correct)
                "Masters", // qualification (incorrect)
                "456 Street", // address (incorrect)
                "OtherCity", // city (incorrect)
                "OtherState", // state (incorrect)
                654321 // pincode (incorrect)
        );

        assertNotEquals(user.getAadharNumber(), incorrectUser.getAadharNumber(), "Aadhar numbers should not match");
        assertNotEquals(user.getPanNumber(), incorrectUser.getPanNumber(), "PAN numbers should not match");
        assertNotEquals(user.getFirstName(), incorrectUser.getFirstName(), "First names should not match");
        assertNotEquals(user.getQualification(), incorrectUser.getQualification(), "Qualifications should not match");
        assertNotEquals(user.getAddress(), incorrectUser.getAddress(), "Addresses should not match");
        assertNotEquals(user.getCity(), incorrectUser.getCity(), "Cities should not match");
        assertNotEquals(user.getState(), incorrectUser.getState(), "States should not match");
        assertNotEquals(user.getPincode(), incorrectUser.getPincode(), "Pincodes should not match");
    }

    // **Test Setter Methods**: Test that setter methods work correctly
    @Test
    public void testUserSetters_Success() {
        user.setFirstName("Shivam");
        user.setLastName("Kumar");
        user.setFname("Rohit");
        user.setMname("Anurag");
        user.setQualification("PhD");
        user.setAddress("456 Street");
        user.setCity("New City");
        user.setState("New State");
        user.setPincode(654321);

        assertEquals("Shivam", user.getFirstName(), "First name should match the updated value");
        assertEquals("Kumar", user.getLastName(), "Last name should match the updated value");
        assertEquals("Rohit", user.getFname(), "Father's name should match the updated value");
        assertEquals("Anurag", user.getMname(), "Mother's name should match the updated value");
        assertEquals("PhD", user.getQualification(), "Qualification should match the updated value");
        assertEquals("456 Street", user.getAddress(), "Address should match the updated value");
        assertEquals("New City", user.getCity(), "City should match the updated value");
        assertEquals("New State", user.getState(), "State should match the updated value");
        assertEquals(654321, user.getPincode(), "Pincode should match the updated value");
    }

    // **Test Setter Methods with Null Values**: Ensure that null values can be set
    @Test
    public void testUserSetters_NullValues() {
        user.setFirstName(null);
        user.setLastName(null);
        user.setFname(null);
        user.setMname(null);
        user.setQualification(null);
        user.setAddress(null);
        user.setCity(null);
        user.setState(null);
        user.setPincode(0);

        assertNull(user.getFirstName(), "First name should be null after setting null");
        assertNull(user.getLastName(), "Last name should be null after setting null");
        assertNull(user.getFname(), "Father's name should be null after setting null");
        assertNull(user.getMname(), "Mother's name should be null after setting null");
        assertNull(user.getQualification(), "Qualification should be null after setting null");
        assertNull(user.getAddress(), "Address should be null after setting null");
        assertNull(user.getCity(), "City should be null after setting null");
        assertNull(user.getState(), "State should be null after setting null");
        assertEquals(0, user.getPincode(), "Pincode should be 0 after setting 0");
    }
}

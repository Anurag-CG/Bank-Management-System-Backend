package com.example.bankservice.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class LoginTest {

    private Login login;
    private User user;

    @BeforeEach
    public void setUp() {
        // Create a sample Date object for testing (e.g., using the current date)
        Date dateOfBirth = new Date();

        // Create the User object using the correct constructor
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

        // Now create Login object with valid userId and password
        login = new Login("aman.kumar", user, "password123");
    }

    // **Success Scenario**: Test Login creation and getter methods
    @Test
    public void testLogin_Success() {
        assertNotNull(login, "Login object should not be null");
        assertEquals("aman.kumar", login.getUserId(), "User ID should match");
        assertEquals(user, login.getUser(), "User should match");
        assertEquals("password123", login.getPassword(), "Password should match");
    }

    // **Failure Scenario**: Test Login object with incorrect userId or password
    @Test
    public void testLogin_Failure() {
        // Creating a new login with different userId or password
        Login incorrectLogin = new Login("rohit.kumar", user, "wrongPassword");

        assertNotEquals(login.getUserId(), incorrectLogin.getUserId(), "User ID should not match");
        assertNotEquals(login.getPassword(), incorrectLogin.getPassword(), "Password should not match");
    }

    // **Success Scenario**: Test Login with setter methods
    @Test
    public void testLoginSetter_Success() {
        login.setUserId("shivam.kumar");
        login.setPassword("newPassword123");

        assertEquals("shivam.kumar", login.getUserId(), "Updated userId should match");
        assertEquals("newPassword123", login.getPassword(), "Updated password should match");
    }

    // **Failure Scenario**: Test setter methods with invalid data
    @Test
    public void testLoginSetter_Failure() {
        login.setUserId(null);
        login.setPassword(null);

        assertNull(login.getUserId(), "UserId should be null after setting null");
        assertNull(login.getPassword(), "Password should be null after setting null");
    }
}

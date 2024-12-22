package com.example.userservice.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    private Login login;
    private User mockUser;

    @BeforeEach
    void setUp() {
        // Create a mock User object (for demonstration purposes)
        mockUser = new User();
        mockUser.setAccountNumber(12345L);
        
        // Set up Login object with constructor
        login = new Login("user123", mockUser, "password123");
    }

    // Test for constructor success
    @Test
    void testLoginConstructor_Success() {
        assertNotNull(login, "Login object should be initialized");
        assertEquals("user123", login.getUserId(), "User ID should be correctly initialized");
        assertEquals(mockUser, login.getUser(), "User should be correctly initialized");
        assertEquals("password123", login.getPassword(), "Password should be correctly initialized");
    }

    // Test for default constructor success (null values)
    @Test
    void testLoginDefaultConstructor_Success() {
        Login defaultLogin = new Login();
        assertNull(defaultLogin.getUserId(), "User ID should be null by default");
        assertNull(defaultLogin.getUser(), "User should be null by default");
        assertNull(defaultLogin.getPassword(), "Password should be null by default");
    }

    // Test for setter and getter of userId
    @Test
    void testSetUserId_Success() {
        login.setUserId("newUserId");
        assertEquals("newUserId", login.getUserId(), "User ID should be updated correctly");
    }

    // Test for setter and getter of password
    @Test
    void testSetPassword_Success() {
        login.setPassword("newPassword123");
        assertEquals("newPassword123", login.getPassword(), "Password should be updated correctly");
    }

    // Test for setter and getter of user (User object)
    @Test
    void testSetUser_Success() {
        User newUser = new User();
        newUser.setAccountNumber(67890L);
        login.setUser(newUser);
        assertEquals(newUser, login.getUser(), "User should be updated correctly");
    }

    // Test for setter and getter of userId (failure case)
    @Test
    void testSetUserId_Failure() {
        login.setUserId("newUserId");
        assertNotEquals("user123", login.getUserId(), "User ID should be changed after update");
    }

    // Test for setter and getter of password (failure case)
    @Test
    void testSetPassword_Failure() {
        login.setPassword("newPassword123");
        assertNotEquals("password123", login.getPassword(), "Password should be changed after update");
    }

    // Test for setter and getter of user (failure case)
    @Test
    void testSetUser_Failure() {
        User newUser = new User();
        newUser.setAccountNumber(67890L);
        login.setUser(newUser);
        assertNotEquals(mockUser, login.getUser(), "User should be updated after setting a new user");
    }
}

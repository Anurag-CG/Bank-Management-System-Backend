package com.example.userservice.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginFormTest {

    private LoginForm loginForm;

    @BeforeEach
    void setUp() {
        // Set up LoginForm object with constructor
        loginForm = new LoginForm(12345L, 987654321L, "user123", "password123");
    }

    // Test for constructor success
    @Test
    void testLoginFormConstructor_Success() {
        assertNotNull(loginForm, "LoginForm object should be initialized");
        assertEquals(12345L, loginForm.getAccountNumber(), "Account number should be correctly initialized");
        assertEquals(987654321L, loginForm.getAadharNumber(), "Aadhar number should be correctly initialized");
        assertEquals("user123", loginForm.getLoginId(), "Login ID should be correctly initialized");
        assertEquals("password123", loginForm.getPassword(), "Password should be correctly initialized");
    }

    // Test for default constructor success (null values)
    @Test
    void testLoginFormDefaultConstructor_Success() {
        LoginForm defaultLoginForm = new LoginForm();
        assertNull(defaultLoginForm.getLoginId(), "Login ID should be null by default");
        assertNull(defaultLoginForm.getPassword(), "Password should be null by default");
        assertNull(defaultLoginForm.getAadharNumber(), "Aadhar number should be null by default");
        assertNull(defaultLoginForm.getAccountNumber(), "Account number should be null by default");
    }

    // Test for setter and getter of accountNumber
    @Test
    void testSetAccountNumber_Success() {
        loginForm.setAccountNumber(67890L);
        assertEquals(67890L, loginForm.getAccountNumber(), "Account number should be updated correctly");
    }

    // Test for setter and getter of aadharNumber
    @Test
    void testSetAadharNumber_Success() {
        loginForm.setAadharNumber(123456789L);
        assertEquals(123456789L, loginForm.getAadharNumber(), "Aadhar number should be updated correctly");
    }

    // Test for setter and getter of loginId
    @Test
    void testSetLoginId_Success() {
        loginForm.setLoginId("newUser123");
        assertEquals("newUser123", loginForm.getLoginId(), "Login ID should be updated correctly");
    }

    // Test for setter and getter of password
    @Test
    void testSetPassword_Success() {
        loginForm.setPassword("newPassword123");
        assertEquals("newPassword123", loginForm.getPassword(), "Password should be updated correctly");
    }

    // Test for setter and getter of accountNumber (failure case)
    @Test
    void testSetAccountNumber_Failure() {
        loginForm.setAccountNumber(67890L);
        assertNotEquals(12345L, loginForm.getAccountNumber(), "Account number should be changed after update");
    }

    // Test for setter and getter of aadharNumber (failure case)
    @Test
    void testSetAadharNumber_Failure() {
        loginForm.setAadharNumber(123456789L);
        assertNotEquals(987654321L, loginForm.getAadharNumber(), "Aadhar number should be changed after update");
    }

    // Test for setter and getter of loginId (failure case)
    @Test
    void testSetLoginId_Failure() {
        loginForm.setLoginId("newUser123");
        assertNotEquals("user123", loginForm.getLoginId(), "Login ID should be changed after update");
    }

    // Test for setter and getter of password (failure case)
    @Test
    void testSetPassword_Failure() {
        loginForm.setPassword("newPassword123");
        assertNotEquals("password123", loginForm.getPassword(), "Password should be changed after update");
    }
}

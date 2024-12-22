package com.example.userservice.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateLoginFormTest {

    private UpdateLoginForm updateLoginForm;

    @BeforeEach
    void setUp() {
        // Set up UpdateLoginForm object with constructor
        updateLoginForm = new UpdateLoginForm("user123", "oldPassword123", "newPassword123");
    }

    // Test for constructor success
    @Test
    void testUpdateLoginFormConstructor_Success() {
        assertNotNull(updateLoginForm, "UpdateLoginForm object should be initialized");
        assertEquals("user123", updateLoginForm.getUserId(), "User ID should be correctly initialized");
        assertEquals("oldPassword123", updateLoginForm.getPreviousPassword(), "Previous password should be correctly initialized");
        assertEquals("newPassword123", updateLoginForm.getNewPassword(), "New password should be correctly initialized");
    }

    // Test for default constructor success (null values)
    @Test
    void testUpdateLoginFormDefaultConstructor_Success() {
        UpdateLoginForm defaultUpdateLoginForm = new UpdateLoginForm();
        assertNull(defaultUpdateLoginForm.getUserId(), "User ID should be null by default");
        assertNull(defaultUpdateLoginForm.getPreviousPassword(), "Previous password should be null by default");
        assertNull(defaultUpdateLoginForm.getNewPassword(), "New password should be null by default");
    }

    // Test for setter and getter of userId
    @Test
    void testSetUserId_Success() {
        updateLoginForm.setUserId("newUser123");
        assertEquals("newUser123", updateLoginForm.getUserId(), "User ID should be updated correctly");
    }

    // Test for setter and getter of previousPassword
    @Test
    void testSetPreviousPassword_Success() {
        updateLoginForm.setPreviousPassword("oldPassword456");
        assertEquals("oldPassword456", updateLoginForm.getPreviousPassword(), "Previous password should be updated correctly");
    }

    // Test for setter and getter of newPassword
    @Test
    void testSetNewPassword_Success() {
        updateLoginForm.setNewPassword("newPassword456");
        assertEquals("newPassword456", updateLoginForm.getNewPassword(), "New password should be updated correctly");
    }

    // Test for setter and getter of userId (failure case)
    @Test
    void testSetUserId_Failure() {
        updateLoginForm.setUserId("newUser123");
        assertNotEquals("user123", updateLoginForm.getUserId(), "User ID should be updated correctly");
    }

    // Test for setter and getter of previousPassword (failure case)
    @Test
    void testSetPreviousPassword_Failure() {
        updateLoginForm.setPreviousPassword("oldPassword456");
        assertNotEquals("oldPassword123", updateLoginForm.getPreviousPassword(), "Previous password should be updated correctly");
    }

    // Test for setter and getter of newPassword (failure case)
    @Test
    void testSetNewPassword_Failure() {
        updateLoginForm.setNewPassword("newPassword456");
        assertNotEquals("newPassword123", updateLoginForm.getNewPassword(), "New password should be updated correctly");
    }
}

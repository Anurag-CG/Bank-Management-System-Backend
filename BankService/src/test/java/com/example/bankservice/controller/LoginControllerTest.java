package com.example.bankservice.controller;

import com.example.bankservice.entities.Login;
import com.example.bankservice.entities.User;
import com.example.bankservice.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    private Login login;
    private User user;

    @BeforeEach
    public void setUp() {
        // Create a mock user with the name "Aman Kumar"
        user = new User();
        user.setFirstName("Aman");
        user.setLastName("Kumar");
        user.setAccountNumber(12345L);

        // Initialize Login object with mock user
        login = new Login();
        login.setUser(user);  // Set the user object
        login.setPassword("password");
    }

    // Test success scenario for adding login
    @Test
    public void testAddLogin_Success() {
        // Mock the service to return a success message
        when(loginService.addLogin(anyLong(), any(Login.class))).thenReturn("Login added successfully");

        // Call the controller method
        ResponseEntity<String> response = loginController.addLogin(12345L, login);

        // Verify that the response status is OK and the response contains the correct message
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login added successfully", response.getBody());

        // Verify that the service method was called with the correct parameters
        verify(loginService, times(1)).addLogin(12345L, login);
    }

    // Test failure scenario for adding login
    @Test
    public void testAddLogin_Failure() {
        // Mock the service to return a failure message
        when(loginService.addLogin(anyLong(), any(Login.class))).thenReturn("Failed to add login");

        // Call the controller method
        ResponseEntity<String> response = loginController.addLogin(12345L, login);

        // Verify that the response status is BAD_REQUEST and the response contains the failure message
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Failed to add login", response.getBody());

        // Verify that the service method was called with the correct parameters
        verify(loginService, times(1)).addLogin(12345L, login);
    }

    // Test success scenario for deleting login
    @Test
    public void testDeleteLogin_Success() {
        // Mock the service to return a success message
        when(loginService.deleteLogin(anyLong(), anyString())).thenReturn("Login deleted successfully");

        // Call the controller method
        ResponseEntity<String> response = loginController.deleteLogin(12345L, "user1");

        // Verify that the response status is OK and the response contains the correct message
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login deleted successfully", response.getBody());

        // Verify that the service method was called with the correct parameters
        verify(loginService, times(1)).deleteLogin(12345L, "user1");
    }

    // Test failure scenario for deleting login
    @Test
    public void testDeleteLogin_Failure() {
        // Mock the service to return a failure message
        when(loginService.deleteLogin(anyLong(), anyString())).thenReturn("Failed to delete login");

        // Call the controller method
        ResponseEntity<String> response = loginController.deleteLogin(12345L, "user1");

        // Verify that the response status is BAD_REQUEST and the response contains the failure message
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Failed to delete login", response.getBody());

        // Verify that the service method was called with the correct parameters
        verify(loginService, times(1)).deleteLogin(12345L, "user1");
    }
}

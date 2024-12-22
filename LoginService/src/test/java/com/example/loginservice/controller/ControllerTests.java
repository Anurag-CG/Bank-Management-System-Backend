package com.example.loginservice.controller;

import com.example.loginservice.entities.Admin;
import com.example.loginservice.entities.BankerLogin;
import com.example.loginservice.entities.Login;
import com.example.loginservice.security.JwtUtil;
import com.example.loginservice.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ControllerTests {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private LoginService loginService;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void adminLogin_ShouldReturnToken_WhenCredentialsAreValid() {
        // Arrange
        Admin admin = new Admin();
        admin.setAdminId("admin123");
        admin.setPassword("adminpass");

        // Mocking the behavior of loginService to return a mocked UserDetails
        when(loginService.authenticateAdmin(anyString(), anyString())).thenReturn(userDetails);

        // Mocking the behavior of JwtUtil to return a token
        when(jwtUtil.generateToken(anyString(), anyString())).thenReturn("validToken");

        // Mock the getUsername method of userDetails to return the expected admin username
        when(userDetails.getUsername()).thenReturn("admin123");

        // Act
        ResponseEntity<String> response = loginController.adminLogin(admin);

        // Assert
        assertNotNull(response);  // Ensure that response is not null
        assertEquals(200, response.getStatusCodeValue());  // Verify that status is 200
        assertEquals("validToken", response.getBody());  // Verify that token is returned
    }


    @Test
    void adminLogin_ShouldReturnUnauthorized_WhenCredentialsAreInvalid() {
        // Arrange
        Admin admin = new Admin();
        admin.setAdminId("admin123");
        admin.setPassword("wrongpass");

        when(loginService.authenticateAdmin(anyString(), anyString())).thenReturn(null);

        // Act
        ResponseEntity<String> response = loginController.adminLogin(admin);

        // Assert
        assertEquals(401, response.getStatusCodeValue());
        assertEquals("Invalid Admin credentials", response.getBody());
    }

    @Test
    void bankerLogin_ShouldReturnToken_WhenCredentialsAreValid() {
        // Arrange
        BankerLogin banker = new BankerLogin();
        banker.setBankerId("banker123");
        banker.setPassword("bankerpass");

        // Mocking the behavior of loginService to return a mocked UserDetails
        when(loginService.authenticateBanker(anyString(), anyString())).thenReturn(userDetails);

        // Mocking the behavior of JwtUtil to return a token
        when(jwtUtil.generateToken(anyString(), anyString())).thenReturn("validToken");

        // Ensure that userDetails.getUsername() returns the expected username
        when(userDetails.getUsername()).thenReturn("banker123");

        // Act
        ResponseEntity<String> response = loginController.bankerLogin(banker);

        // Assert
        assertNotNull(response);  // Ensure that response is not null
        assertEquals(200, response.getStatusCodeValue());  // Verify that status is 200
        assertEquals("validToken", response.getBody());  // Verify that token is returned
    }


    @Test
    void bankerLogin_ShouldReturnUnauthorized_WhenCredentialsAreInvalid() {
        // Arrange
        BankerLogin banker = new BankerLogin();
        banker.setBankerId("banker123");
        banker.setPassword("wrongpass");

        when(loginService.authenticateBanker(anyString(), anyString())).thenReturn(null);

        // Act
        ResponseEntity<String> response = loginController.bankerLogin(banker);

        // Assert
        assertEquals(401, response.getStatusCodeValue());
        assertEquals("Invalid Banker credentials", response.getBody());
    }

    @Test
    void userLogin_ShouldReturnToken_WhenCredentialsAreValid() {
        // Arrange
        Login login = new Login();
        login.setUserId("user123");
        login.setPassword("userpass");

        // Mocking the behavior of loginService and jwtUtil
        when(loginService.authenticateUser(anyString(), anyString())).thenReturn(userDetails);
        
        // Mocking the behavior of JwtUtil
        when(jwtUtil.generateToken(anyString(), anyString())).thenReturn("validToken");

        // Ensure that userDetails is properly initialized with a username
        when(userDetails.getUsername()).thenReturn("user123");

        // Act
        ResponseEntity<String> response = loginController.userLogin(login);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("validToken", response.getBody());
    }


    @Test
    void userLogin_ShouldReturnUnauthorized_WhenCredentialsAreInvalid() {
        // Arrange
        Login login = new Login();
        login.setUserId("user123");
        login.setPassword("wrongpass");

        when(loginService.authenticateUser(anyString(), anyString())).thenReturn(null);

        // Act
        ResponseEntity<String> response = loginController.userLogin(login);

        // Assert
        assertEquals(401, response.getStatusCodeValue());
        assertEquals("Invalid User credentials", response.getBody());
    }
}

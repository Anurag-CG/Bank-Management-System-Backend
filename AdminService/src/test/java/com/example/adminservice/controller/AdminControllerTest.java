package com.example.adminservice.controller;

import com.example.adminservice.entities.BankerLogin;
import com.example.adminservice.entities.User;
import com.example.adminservice.service.AdminServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    @Mock
    private AdminServices adminServices;

    @InjectMocks
    private AdminController adminController;

    private BankerLogin banker;
    private User user;

    // Initialize objects before each test
    @BeforeEach
    void setUp() {
        // Creating a User instance with sample data
        user = new User(123456L, 9876543210L, "ABCDE1234F", "Alice", "Smith", "John", "Doe",
                new Date(), "MSc", "123 Street, City", "CityName", "StateName", 123456);

        // Creating a BankerLogin instance with sample data
        banker = new BankerLogin("B123", "John Doe", "password123");
    }

    // Test adding a banker successfully
    @Test
    void testAddBanker_Success() {
        // Mock service method for success scenario
        Mockito.when(adminServices.addBanker(banker)).thenReturn("Banker added successfully");

        // Call the controller method and assert success
        ResponseEntity<String> response = adminController.addBanker(banker);
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status should be OK for successful addition");
    }

    // Test adding a banker with failure
    @Test
    void testAddBanker_Failure() {
        // Mock service method for failure scenario
        Mockito.when(adminServices.addBanker(banker)).thenReturn("Error: Unable to add banker");

        // Call the controller method and assert failure
        ResponseEntity<String> response = adminController.addBanker(banker);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Status should be BAD_REQUEST for failure");
    }

    // Test removing a banker successfully
    @Test
    void testRemoveBanker_Success() {
        // Mock service method for success scenario
        Mockito.when(adminServices.removeBanker(banker)).thenReturn("Banker removed successfully");

        // Call the controller method and assert success
        ResponseEntity<String> response = adminController.removeBanker(banker);
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status should be OK for successful removal");
    }

    // Test removing a banker with failure
    @Test
    void testRemoveBanker_Failure() {
        // Mock service method for failure scenario
        Mockito.when(adminServices.removeBanker(banker)).thenReturn("Error: Banker not found");

        // Call the controller method and assert failure
        ResponseEntity<String> response = adminController.removeBanker(banker);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Status should be BAD_REQUEST for failure");
    }

    // Test viewing a banker successfully
    @Test
    void testViewBanker_Success() {
        // Mock service method for success scenario
        Mockito.when(adminServices.searchBanker("B123")).thenReturn(banker);

        // Call the controller method and assert success
        ResponseEntity<BankerLogin> response = adminController.showBanker("B123");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status should be OK for successful banker retrieval");
    }

    // Test viewing a banker with failure (banker not found)
    @Test
    void testViewBanker_Failure() {
        // Mock service method for failure scenario
        Mockito.when(adminServices.searchBanker("B123")).thenReturn(null);

        // Call the controller method and assert failure
        ResponseEntity<BankerLogin> response = adminController.showBanker("B123");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Status should be BAD_REQUEST when banker is not found");
    }

    // Test viewing a user successfully
    @Test
    void testViewUser_Success() {
        // Mock service method for success scenario
        Mockito.when(adminServices.searchUser(123456L)).thenReturn(user);

        // Call the controller method and assert success
        ResponseEntity<User> response = adminController.searchUser(123456L);
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status should be OK for successful user retrieval");
    }

    // Test viewing a user with failure (user not found)
    @Test
    void testViewUser_Failure() {
        // Mock service method for failure scenario
        Mockito.when(adminServices.searchUser(123456L)).thenReturn(null);

        // Call the controller method and assert failure
        ResponseEntity<User> response = adminController.searchUser(123456L);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Status should be BAD_REQUEST when user is not found");
    }
}

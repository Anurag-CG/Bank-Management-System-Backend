package com.example.adminservice.service;

import com.example.adminservice.entities.BankerLogin;
import com.example.adminservice.entities.User;
import com.example.adminservice.repository.BankerRepository;
import com.example.adminservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminServicesTest {

    @Mock
    private BankerRepository bankerRepo;  // Mocking the BankerRepository

    @Mock
    private UserRepository userRepo;  // Mocking the UserRepository

    @Mock
    private IDGeneratorService idGeneratorService;  // Mocking IDGeneratorService

    @InjectMocks
    private AdminServices adminServices;  // Injecting mocks into AdminServices

    private BankerLogin banker;
    private User user;

    // Initialize objects before each test
    @BeforeEach
    void setUp() {
        // Creating a BankerLogin instance with sample data
        banker = new BankerLogin("B123", "Aman Kumar", "password123");

        // Creating a User instance with sample data
        user = new User(123456L, 9876543210L, "ABCDE1234F", "Shivam", "Kumar", "Rohit", "Kumar",
                new java.util.Date(), "MSc", "123 Street, City", "CityName", "StateName", 123456);
    }

    // Test adding a banker successfully
    @Test
    void testAddBanker_Success() {
        // Mock the ID generation
        when(idGeneratorService.generateID()).thenReturn("B123");

        // Mock the repository behavior
        when(bankerRepo.findById("B123")).thenReturn(java.util.Optional.empty());  // No existing banker with this ID

        // Mock password encoding
        when(bankerRepo.save(banker)).thenReturn(banker);

        // Call the service method and assert the result
        String result = adminServices.addBanker(banker);
        assertTrue(result.contains("Banker Created successfully"), "Banker should be created successfully");
    }

    // Test adding a banker failure (banker already exists)
    @Test
    void testAddBanker_Failure() {
        // Mock the ID generation
        when(idGeneratorService.generateID()).thenReturn("B123");

        // Mock the repository behavior (this banker already exists)
        when(bankerRepo.findById("B123")).thenReturn(java.util.Optional.of(banker));

        // Call the service method and assert the result
        String result = adminServices.addBanker(banker);
        assertTrue(result.contains("Error creating banker"), "Banker should not be created if ID already exists");
    }

    // Test removing a banker successfully
    @Test
    void testRemoveBanker_Success() {
        // Mock the repository behavior (existing banker)
        when(bankerRepo.findById("B123")).thenReturn(java.util.Optional.of(banker));

        // Mock deletion behavior
        //Mockito.when(bankerRepo.delete(banker)).thenReturn(null);
        Mockito.doNothing().when(bankerRepo).delete(banker);
        
        // Call the service method and assert the result
        String result = adminServices.removeBanker(banker);
        assertTrue(result.contains("Banker deleted successfully"), "Banker should be deleted successfully");
    }

    // Test removing a banker failure (banker not found)
    @Test
    void testRemoveBanker_BankerNotFound() {
        // Mock the repository behavior (no such banker exists)
        when(bankerRepo.findById("B123")).thenReturn(java.util.Optional.empty());

        // Call the service method and assert the result
        String result = adminServices.removeBanker(banker);
        assertTrue(result.contains("Banker Id not matched"), "Banker should not be deleted if not found");
    }

    // Test removing a banker failure (banker details do not match)
    @Test
    void testRemoveBanker_DetailsDoNotMatch() {
        // Mock the repository behavior (existing banker)
        BankerLogin existingBanker = new BankerLogin("B123", "Jane Doe", "password123");
        when(bankerRepo.findById("B123")).thenReturn(java.util.Optional.of(existingBanker));

        // Call the service method and assert the result
        String result = adminServices.removeBanker(banker);
        assertTrue(result.contains("Banker details not matched"), "Banker should not be deleted if details do not match");
    }

    // Test searching for a banker successfully
    @Test
    void testSearchBanker_Success() {
        // Mock the repository behavior (existing banker)
        when(bankerRepo.findById("B123")).thenReturn(java.util.Optional.of(banker));

        // Call the service method and assert the result
        BankerLogin foundBanker = adminServices.searchBanker("B123");
        assertNotNull(foundBanker, "Banker should be found");
        assertEquals("B123", foundBanker.getBankerId(), "Banker ID should match");
    }

    // Test searching for a banker failure (banker not found)
    @Test
    void testSearchBanker_Failure() {
        // Mock the repository behavior (no such banker exists)
        when(bankerRepo.findById("B123")).thenReturn(java.util.Optional.empty());

        // Call the service method and assert the result
        BankerLogin foundBanker = adminServices.searchBanker("B123");
        assertNull(foundBanker, "Banker should not be found");
    }

    // Test searching for a user successfully
    @Test
    void testSearchUser_Success() {
        // Mock the repository behavior (existing user)
        when(userRepo.findById(123456L)).thenReturn(java.util.Optional.of(user));

        // Call the service method and assert the result
        User foundUser = adminServices.searchUser(123456L);
        assertNotNull(foundUser, "User should be found");
        assertEquals(123456L, foundUser.getAccountNumber(), "User account number should match");
    }

    // Test searching for a user failure (user not found)
    @Test
    void testSearchUser_Failure() {
        // Mock the repository behavior (no such user exists)
        when(userRepo.findById(123456L)).thenReturn(java.util.Optional.empty());

        // Call the service method and assert the result
        User foundUser = adminServices.searchUser(123456L);
        assertNull(foundUser, "User should not be found");
    }
}

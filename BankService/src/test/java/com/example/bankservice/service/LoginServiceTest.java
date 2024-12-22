package com.example.bankservice.service;

import com.example.bankservice.entities.Login;
import com.example.bankservice.entities.User;
import com.example.bankservice.repository.LoginRepository;
import com.example.bankservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginServiceTest {

    @InjectMocks
    private LoginService loginService;

    @Mock
    private LoginRepository loginRepo;

    @Mock
    private UserRepository userRepo;

    @Mock
    private BCryptPasswordEncoder encoder;

    private Login login;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup test data for User with specific names
        user = new User();
        user.setAccountNumber(123456L);
        user.setAadharNumber(123456789012L);
        user.setPanNumber("ABCDE1234F");
        user.setFirstName("Aman");
        user.setLastName("Kumar Singh");
        user.setFname("Rajesh");
        user.setMname("Neelam");
        user.setDateOfBirth(new Date());
        user.setQualification("Master's Degree");
        user.setAddress("456 Park Avenue");
        user.setCity("Delhi");
        user.setState("Delhi");
        user.setPincode(110001);

        // Setup test data for Login
        login = new Login();
        login.setUserId("nikhil123");
        login.setPassword("password123");

        // Assign the user to the login object (this was missing before)
        login.setUser(user);  // This is crucial to avoid NPE
    }

    // Test for deleteLogin method (Success)
    @Test
    void testDeleteLogin_Success() {
        // Mocking the repository responses
        when(loginRepo.findById("nikhil123")).thenReturn(Optional.of(login));  // Find login by userId
        when(userRepo.findById(123456L)).thenReturn(Optional.of(user));  // Ensure that the account number is valid

        // Call the method to test
        String result = loginService.deleteLogin(123456L, "nikhil123");

        // Verify the behavior and result
        assertEquals("User deleted successfully", result);
        verify(loginRepo, times(1)).delete(login);  // Ensure delete was called
    }

    // Test for deleteLogin method (LoginId not valid)
    @Test
    void testDeleteLogin_LoginIdNotValid() {
        // Mocking the repository responses
        when(loginRepo.findById("nikhil123")).thenReturn(Optional.empty());  // No login found for the userId

        // Call the method to test
        String result = loginService.deleteLogin(123456L, "nikhil123");

        // Verify the behavior and result
        assertEquals("LoginId not valid", result);
        verify(loginRepo, never()).delete(any(Login.class));  // Ensure delete is not called
    }

    // Test for deleteLogin method (Account number does not match)
    @Test
    void testDeleteLogin_AccountNumberMismatch() {
        // Mocking the repository responses
        when(loginRepo.findById("nikhil123")).thenReturn(Optional.of(login));  // Login found
        User differentUser = new User();
        differentUser.setAccountNumber(654321L);  // Different account number
        login.setUser(differentUser);  // Set the login to a user with different account number

        // Call the method to test
        String result = loginService.deleteLogin(123456L, "nikhil123");

        // Verify the behavior and result
        assertEquals("Account number not matching", result);
        verify(loginRepo, never()).delete(any(Login.class));  // Ensure delete is not called
    }
}

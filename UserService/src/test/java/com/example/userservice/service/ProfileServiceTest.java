package com.example.userservice.service;

import com.example.userservice.entities.*;
import com.example.userservice.repository.BalanceRepository;
import com.example.userservice.repository.LoginRepository;
import com.example.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfileServiceTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private LoginRepository loginRepo;

    @Mock
    private BalanceRepository balanceRepo;

    @InjectMocks
    private ProfileService profileService;

    private User user;
    private Login login;
    private LoginForm loginForm;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initializes mocks

        // Initialize test data
        user = new User();
        user.setAccountNumber(1234567890L);
        user.setAadharNumber(123456789012L);
        user.setPanNumber("ABCD1234E");

        login = new Login("user1", user, "password123");

        loginForm = new LoginForm();
        loginForm.setAccountNumber(1234567890L);
        loginForm.setAadharNumber(123456789012L);
        loginForm.setLoginId("user1");
        loginForm.setPassword("password123");
    }

    // Test case for addUser method
    @Test
    void testAddUser_Success() {
        // Mocking behavior of userRepo
        when(userRepo.existsById(user.getAccountNumber())).thenReturn(false);
        when(userRepo.existsByAadharNumber(user.getAadharNumber())).thenReturn(false);
        when(userRepo.existsByPanNumber(user.getPanNumber())).thenReturn(false);

        // Mock BalanceRepository behavior
        when(balanceRepo.save(any(Funds.class))).thenReturn(new Funds());

        // Call the method
        String result = profileService.addUser(user);

        // Verify the result
        assertTrue(result.contains("Acccount created"));
        verify(userRepo, times(1)).save(user);
        verify(balanceRepo, times(1)).save(any(Funds.class));
    }

    // Test case for addUser when Aadhar already exists
    @Test
    void testAddUser_AadharAlreadyExists() {
        when(userRepo.existsByAadharNumber(user.getAadharNumber())).thenReturn(true);

        String result = profileService.addUser(user);

        assertEquals("Aadhar Number already exists", result);
        verify(userRepo, never()).save(user);  // Ensure user isn't saved
    }

    // Test case for addLogin method where Aadhar doesn't match
    @Test
    void testAddLogin_AadharNotMatched() {
        // Mock the user repository to find user
        when(userRepo.findById(loginForm.getAccountNumber())).thenReturn(java.util.Optional.of(user));

        // Set wrong Aadhar Number
        loginForm.setAadharNumber(987654321098L);

        String result = profileService.addLogin(loginForm);

        assertEquals("Aadhar Not Matched", result);
    }

    // Test case for addLogin where user is not found
    @Test
    void testAddLogin_UserNotFound() {
        // Mock the user repository to return empty
        when(userRepo.findById(loginForm.getAccountNumber())).thenReturn(java.util.Optional.empty());

        String result = profileService.addLogin(loginForm);

        assertEquals("Please enter valid Account Number", result);
    }

    // Test case for addLogin when login is already created
    @Test
    void testAddLogin_AccountAlreadyExists() {
        when(userRepo.findById(loginForm.getAccountNumber())).thenReturn(java.util.Optional.of(user));
        when(loginRepo.findByUser(user)).thenReturn(login); // Mock existing login

        String result = profileService.addLogin(loginForm);

        assertEquals("Account already exists", result);
    }

    // Test case for successful login creation
    @Test
    void testAddLogin_Success() {
        when(userRepo.findById(loginForm.getAccountNumber())).thenReturn(java.util.Optional.of(user));
        when(loginRepo.findByUser(user)).thenReturn(null);
        when(loginRepo.findById(loginForm.getLoginId())).thenReturn(java.util.Optional.empty());

        String result = profileService.addLogin(loginForm);

        assertEquals("LoginId created successfully", result);
        verify(loginRepo, times(1)).save(any(Login.class));  // Verify that loginRepo.save() was called
    }

    // Test case for updateUser method
    @Test
    void testUpdateUser() {
        when(userRepo.save(user)).thenReturn(user);  // Mocking save behavior

        User updatedUser = profileService.updateUser(user);

        assertEquals(user, updatedUser);
        verify(userRepo, times(1)).save(user);  // Verify userRepo.save() was called
    }

    // Test case for updateLogin method where password doesn't match
    @Test
    void testUpdateLogin_PasswordNotMatched() {
        UpdateLoginForm form = new UpdateLoginForm("user1", "wrongPassword", "newPassword123");
        when(loginRepo.findById(form.getUserId())).thenReturn(java.util.Optional.of(login));

        Login updatedLogin = profileService.updateLogin(form);

        assertNull(updatedLogin);  // Should return null if passwords do not match
    }
}

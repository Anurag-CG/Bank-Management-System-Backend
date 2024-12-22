package com.example.loginservice.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.loginservice.entities.Admin;
import com.example.loginservice.entities.BankerLogin;
import com.example.loginservice.entities.Login;
import com.example.loginservice.repository.AdminRepository;
import com.example.loginservice.repository.BankerRepository;
import com.example.loginservice.repository.LoginRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

class ServiceTests {

    @Mock
    private AdminRepository adminRepo;

    @Mock
    private BankerRepository bankerRepo;

    @Mock
    private LoginRepository loginRepo;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private LoginService loginService;

    private Admin admin;
    private BankerLogin banker;
    private Login user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks

        // Sample data for testing
        admin = new Admin();
        admin.setAdminId("admin123");
        admin.setPassword("adminpass");

        banker = new BankerLogin();
        banker.setBankerId("banker123");
        banker.setPassword("bankerpass");

        user = new Login();
        user.setUserId("user123");
        user.setPassword("userpass");
    }

    // Test for authenticateAdmin
    @Test
    void authenticateAdmin_ShouldReturnUserDetails_WhenCredentialsAreValid() {
        // Arrange
        when(adminRepo.findById(admin.getAdminId())).thenReturn(Optional.of(admin));

        // Act
        UserDetails userDetails = loginService.authenticateAdmin(admin.getAdminId(), admin.getPassword());

        // Assert
        assertNotNull(userDetails);
        assertEquals(admin.getAdminId(), userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
    }

    @Test
    void authenticateAdmin_ShouldReturnNull_WhenAdminNotFound() {
        // Arrange
        when(adminRepo.findById(admin.getAdminId())).thenReturn(Optional.empty());

        // Act
        UserDetails userDetails = loginService.authenticateAdmin(admin.getAdminId(), admin.getPassword());

        // Assert
        assertNull(userDetails);
    }

    @Test
    void authenticateAdmin_ShouldReturnNull_WhenPasswordIsIncorrect() {
        // Arrange
        when(adminRepo.findById(admin.getAdminId())).thenReturn(Optional.of(admin));

        // Act
        UserDetails userDetails = loginService.authenticateAdmin(admin.getAdminId(), "wrongpass");

        // Assert
        assertNull(userDetails);
    }

    // Test for authenticateBanker
    @Test
    void authenticateBanker_ShouldReturnUserDetails_WhenCredentialsAreValid() {
        // Arrange
        when(bankerRepo.findById(banker.getBankerId())).thenReturn(Optional.of(banker));
        when(encoder.matches(banker.getPassword(), banker.getPassword())).thenReturn(true);

        // Act
        UserDetails userDetails = loginService.authenticateBanker(banker.getBankerId(), banker.getPassword());

        // Assert
        assertNotNull(userDetails);
        assertEquals(banker.getBankerId().toString(), userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_BANKER")));
    }

    @Test
    void authenticateBanker_ShouldReturnNull_WhenBankerNotFound() {
        // Arrange
        when(bankerRepo.findById(banker.getBankerId())).thenReturn(Optional.empty());

        // Act
        UserDetails userDetails = loginService.authenticateBanker(banker.getBankerId(), banker.getPassword());

        // Assert
        assertNull(userDetails);
    }

    @Test
    void authenticateBanker_ShouldReturnNull_WhenPasswordIsIncorrect() {
        // Arrange
        when(bankerRepo.findById(banker.getBankerId())).thenReturn(Optional.of(banker));
        when(encoder.matches(banker.getPassword(), "wrongpass")).thenReturn(false);

        // Act
        UserDetails userDetails = loginService.authenticateBanker(banker.getBankerId(), "wrongpass");

        // Assert
        assertNull(userDetails);
    }

    // Test for authenticateUser
    @Test
    void authenticateUser_ShouldReturnUserDetails_WhenCredentialsAreValid() {
        // Arrange
        when(loginRepo.findById(user.getUserId())).thenReturn(Optional.of(user));
        when(encoder.matches(user.getPassword(), user.getPassword())).thenReturn(true);

        // Act
        UserDetails userDetails = loginService.authenticateUser(user.getUserId(), user.getPassword());

        // Assert
        assertNotNull(userDetails);
        assertEquals(user.getUserId(), userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
    }

    @Test
    void authenticateUser_ShouldReturnNull_WhenUserNotFound() {
        // Arrange
        when(loginRepo.findById(user.getUserId())).thenReturn(Optional.empty());

        // Act
        UserDetails userDetails = loginService.authenticateUser(user.getUserId(), user.getPassword());

        // Assert
        assertNull(userDetails);
    }

    @Test
    void authenticateUser_ShouldReturnNull_WhenPasswordIsIncorrect() {
        // Arrange
        when(loginRepo.findById(user.getUserId())).thenReturn(Optional.of(user));
        when(encoder.matches(user.getPassword(), "wrongpass")).thenReturn(false);

        // Act
        UserDetails userDetails = loginService.authenticateUser(user.getUserId(), "wrongpass");

        // Assert
        assertNull(userDetails);
    }
}

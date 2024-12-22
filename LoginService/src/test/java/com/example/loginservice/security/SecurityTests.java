package com.example.loginservice.security;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.Date;

class SecurityTests {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private String token;
    private String validUsername = "user123";
    private String validRole = "ROLE_USER";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks

        // Sample token creation using JwtUtil
        token = "sample.jwt.token";  // Replace with an actual token for real tests
    }

 

    @Test
    void doFilterInternal_ShouldNotAuthenticateUser_WhenTokenIsInvalid() throws ServletException, IOException {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtUtil.validateToken(anyString(), anyString())).thenReturn(false);

        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void doFilterInternal_ShouldNotAuthenticateUser_WhenNoTokenPresent() throws ServletException, IOException {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn(null);

        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }


    @Test
    void extractUsername_ShouldReturnCorrectUsername() {
        // Arrange
        when(jwtUtil.extractUsername(token)).thenReturn(validUsername);

        // Act
        String username = jwtUtil.extractUsername(token);

        // Assert
        assertEquals(validUsername, username);
    }

    @Test
    void extractRole_ShouldReturnCorrectRole() {
        // Arrange
        when(jwtUtil.extractRole(token)).thenReturn(validRole);

        // Act
        String role = jwtUtil.extractRole(token);

        // Assert
        assertEquals(validRole, role);
    }

    @Test
    void validateToken_ShouldReturnFalse_WhenTokenIsExpired() {
        // Arrange
        when(jwtUtil.extractUsername(token)).thenReturn(validUsername);
        when(jwtUtil.isTokenExpired(token)).thenReturn(true);

        // Act
        boolean isValid = jwtUtil.validateToken(token, validUsername);

        // Assert
        assertFalse(isValid);
    }

    @Test
    void validateToken_ShouldReturnFalse_WhenUsernameDoesNotMatch() {
        // Arrange
        when(jwtUtil.extractUsername(token)).thenReturn("wrongUsername");

        // Act
        boolean isValid = jwtUtil.validateToken(token, validUsername);

        // Assert
        assertFalse(isValid);
    }

    @Test
    void isTokenExpired_ShouldReturnFalse_WhenTokenIsNotExpired() {
        // Arrange
        Date futureDate = new Date(System.currentTimeMillis() + 1000); // A future date
        when(jwtUtil.extractExpiration(token)).thenReturn(futureDate);

        // Act
        boolean isExpired = jwtUtil.isTokenExpired(token);

        // Assert
        assertFalse(isExpired);
    }
}

package com.example.userservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class) // This annotation is used to initialize mocks automatically
class JwtAuthenticationFilterTest {

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Mock(lenient = true)
    private JwtUtil jwtUtil;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    // Setup method to clear the SecurityContext before each test
    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext();  // Clear SecurityContext before each test
    }

    // Test case when token is valid, should set authentication in SecurityContext
    @Test
    void testDoFilterInternal_ValidToken_ShouldSetAuthentication() throws Exception {
        String token = "validToken";
        String username = "user1";
        String role = "ROLE_USER";

        // Mocking JwtUtil behavior
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtUtil.validateToken(token, username)).thenReturn(true);
        when(jwtUtil.extractUsername(token)).thenReturn(username);
        when(jwtUtil.extractRole(token)).thenReturn(role);

        // Call the method to test
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert the SecurityContext is set with authentication
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals(username, SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        assertTrue(SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("USER")));
    }

    // Test case when token is invalid, should not set authentication in SecurityContext
    @Test
    void testDoFilterInternal_InvalidToken_ShouldNotSetAuthentication() throws Exception {
        String token = "invalidToken";
        String username = "user1";

        // Mocking JwtUtil behavior for an invalid token
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtUtil.validateToken(token, username)).thenReturn(false);

        // Call the method to test
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert the SecurityContext is not set (authentication should be null)
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }
}

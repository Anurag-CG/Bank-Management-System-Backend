package com.example.bankservice.controller;

import com.example.bankservice.entities.FundTransfer;
import com.example.bankservice.entities.User;
import com.example.bankservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private User user;
    private FundTransfer fundTransfer;

    @BeforeEach
    public void setUp() {
        // Initialize the mocks
        MockitoAnnotations.openMocks(this);
        
        // Create a sample user and fund transfer for testing
        user = new User(12345L, 123456789012L, "ABCDE1234F", "Aman", "Kumar", "Father Name", "Mother Name", null, "MSc", "Address", "City", "State", 123456);
        fundTransfer = new FundTransfer(12345L, 67890L, 1000L);
    }

    // Test Case for successful addUser
    @Test
    public void testAddUser_Success() {
        when(userService.addUser(user)).thenReturn("User added successfully");

        ResponseEntity<String> response = userController.addUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User added successfully", response.getBody());
        verify(userService, times(1)).addUser(user);
    }

    // Test Case for failed addUser
    @Test
    public void testAddUser_Failure() {
        when(userService.addUser(user)).thenReturn("Failed to add user");

        ResponseEntity<String> response = userController.addUser(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Failed to add user", response.getBody());
        verify(userService, times(1)).addUser(user);
    }

    // Test Case for deleting a user (success)
    @Test
    public void testDeleteUser_Success() {
        when(userService.deleteUser(12345L, 123456789012L)).thenReturn("Please return the amount: 1000");

        ResponseEntity<String> response = userController.deleteUser(12345L, 123456789012L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Please return the amount: 1000", response.getBody());
        verify(userService, times(1)).deleteUser(12345L, 123456789012L);
    }

    // Test Case for failed deleteUser
    @Test
    public void testDeleteUser_Failure() {
        when(userService.deleteUser(12345L, 123456789012L)).thenReturn("User deletion failed");

        ResponseEntity<String> response = userController.deleteUser(12345L, 123456789012L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User deletion failed", response.getBody());
        verify(userService, times(1)).deleteUser(12345L, 123456789012L);
    }

    // Test Case for finding a user (success)
    @Test
    public void testFindUser_Success() {
        when(userService.findUser(12345L)).thenReturn(user);

        ResponseEntity<Object> response = userController.findUser(12345L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).findUser(12345L);
    }

    // Test Case for failed findUser (user not found)
    @Test
    public void testFindUser_Failure() {
        when(userService.findUser(12345L)).thenReturn(null);

        ResponseEntity<Object> response = userController.findUser(12345L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User not found", response.getBody());
        verify(userService, times(1)).findUser(12345L);
    }

    // Test Case for updating a user
    @Test
    public void testUpdateUser() {
        when(userService.updateUser(user)).thenReturn(user);

        ResponseEntity<User> response = userController.updateUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).updateUser(user);
    }

    // Test Case for viewing balance (success)
    @Test
    public void testViewBalance_Success() {
        when(userService.viewFund(12345L)).thenReturn(5000L);

        ResponseEntity<Object> response = userController.viewFund(12345L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5000L, response.getBody());
        verify(userService, times(1)).viewFund(12345L);
    }

    // Test Case for failed viewBalance (balance not found)
    @Test
    public void testViewBalance_Failure() {
        when(userService.viewFund(12345L)).thenReturn(null);

        ResponseEntity<Object> response = userController.viewFund(12345L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error faced fetching balance", response.getBody());
        verify(userService, times(1)).viewFund(12345L);
    }

    // Test Case for successful fund transfer
    @Test
    public void testFundTransfer_Success() {
        when(userService.transferFund(fundTransfer)).thenReturn("Fund transfer successfully completed");

        ResponseEntity<String> response = userController.fundTransfer(fundTransfer);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Fund transfer successfully completed", response.getBody());
        verify(userService, times(1)).transferFund(fundTransfer);
    }

    // Test Case for failed fund transfer
    @Test
    public void testFundTransfer_Failure() {
        when(userService.transferFund(fundTransfer)).thenReturn("Transfer failed due to insufficient funds");

        ResponseEntity<String> response = userController.fundTransfer(fundTransfer);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Transfer failed due to insufficient funds", response.getBody());
        verify(userService, times(1)).transferFund(fundTransfer);
    }

    // Test Case for successful credit transaction
    @Test
    public void testCreditTransaction_Success() {
        when(userService.addFund(500L, 12345L)).thenReturn("Amount credited successfully");

        ResponseEntity<Object> response = userController.creditTransaaction(500L, 12345L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Amount credited successfully", response.getBody());
        verify(userService, times(1)).addFund(500L, 12345L);
    }

    // Test Case for failed credit transaction
    @Test
    public void testCreditTransaction_Failure() {
        when(userService.addFund(500L, 12345L)).thenReturn("Amount not credited due to some error");

        ResponseEntity<Object> response = userController.creditTransaaction(500L, 12345L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Amount not credited due to some error", response.getBody());
        verify(userService, times(1)).addFund(500L, 12345L);
    }

    // Test Case for successful debit transaction
    @Test
    public void testDebitTransaction_Success() {
        when(userService.debitFund(500L, 12345L)).thenReturn("Amount debited successfully");

        ResponseEntity<String> response = userController.debitTransaction(500L, 12345L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Amount debited successfully", response.getBody());
        verify(userService, times(1)).debitFund(500L, 12345L);
    }

    // Test Case for failed debit transaction
    @Test
    public void testDebitTransaction_Failure() {
        when(userService.debitFund(500L, 12345L)).thenReturn("Amount not debited due to insufficient balance");

        ResponseEntity<String> response = userController.debitTransaction(500L, 12345L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Amount not debited due to insufficient balance", response.getBody());
        verify(userService, times(1)).debitFund(500L, 12345L);
    }
}

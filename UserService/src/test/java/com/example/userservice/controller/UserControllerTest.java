package com.example.userservice.controller;

import com.example.userservice.entities.FundTransfer;
import com.example.userservice.entities.Login;
import com.example.userservice.entities.LoginForm;
import com.example.userservice.entities.UpdateLoginForm;
import com.example.userservice.entities.User;
import com.example.userservice.service.FundService;
import com.example.userservice.service.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private FundService fundService;

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private UserController userController;

    private User user;
    private Login login;
    private LoginForm loginForm;
    private UpdateLoginForm updateLoginForm;
    private FundTransfer fundTransfer;

    @BeforeEach
    void setUp() {
        // Mock objects for tests
        user = new User(123456L, 9876543210L, "ABCDE1234F", "Aman", "Kumar", "Shivam", "Rohit", null, "Bachelor", "123 Main St", "Patna", "Bihar", 800001);
        login = new Login("user123", user, "password");
        loginForm = new LoginForm(123456L, 9876543210L, "user123", "password");
        updateLoginForm = new UpdateLoginForm("user123", "oldPassword", "newPassword");
        fundTransfer = new FundTransfer(123456L, 654321L, 500L);
    }

    // Test for adding user
    @Test
    void testAddUser() {
        when(profileService.addUser(user)).thenReturn("User added successfully");

        String result = userController.addUser(user).getBody();

        assertEquals("User added successfully", result);
    }

    // Test for adding login
    @Test
    void testAddLogin() {
        when(profileService.addLogin(loginForm)).thenReturn("Login added successfully");

        String result = userController.addLogin(loginForm).getBody();

        assertEquals("Login added successfully", result);
    }

    // Test for updating user
    @Test
    void testUpdateUser() {
        when(profileService.updateUser(user)).thenReturn(user);

        User updatedUser = userController.updateUser(user).getBody();

        assertNotNull(updatedUser);
        assertEquals("Aman", updatedUser.getFirstName());
        assertEquals("Kumar", updatedUser.getLastName());
    }

    // Test for finding user
    @Test
    void testFindUser() {
        when(profileService.getAccount(login)).thenReturn(user);

        User foundUser = userController.getUser(login).getBody();

        assertNotNull(foundUser);
        assertEquals("Aman", foundUser.getFirstName());
        assertEquals("Kumar", foundUser.getLastName());
    }

    // Test for viewing balance
    @Test
    void testViewBalance() {
        when(fundService.getBalance("user123")).thenReturn(1000L);

        Long balance = (Long) userController.availableBalance("user123").getBody();

        assertNotNull(balance);
        assertEquals(1000L, balance);
    }

    // Test for updating login password
    @Test
    void testUpdatePassword() {
        when(profileService.updateLogin(updateLoginForm)).thenReturn(new Login("user123", user, "newPassword"));

        Login updatedLogin = userController.updatePassword(updateLoginForm).getBody();

        assertNotNull(updatedLogin);
        assertEquals("newPassword", updatedLogin.getPassword());
    }

    // Test for fund transfer
    @Test
    void testTransferFund() {
        when(fundService.transferFund(fundTransfer)).thenReturn("Transaction Successful");

        String txnResult = userController.transferFund(fundTransfer).getBody();

        assertNotNull(txnResult);
        assertEquals("Transaction Successful", txnResult);
    }
}

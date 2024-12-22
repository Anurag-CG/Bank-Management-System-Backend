package com.example.bankservice.service;

import com.example.bankservice.entities.FundTransfer;
import com.example.bankservice.entities.Funds;
import com.example.bankservice.entities.User;
import com.example.bankservice.repository.BalanceRepository;
import com.example.bankservice.repository.LoginRepository;
import com.example.bankservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepo;

    @Mock
    private LoginRepository loginRepo;

    @Mock
    private BalanceRepository balanceRepo;

    private User senderUser;
    private User receiverUser;
    private Funds senderFunds;
    private Funds receiverFunds;
    private FundTransfer transaction;

    @BeforeEach
    public void setUp() {
        senderUser = new User(12345L, 123456789012L, "ABCDE1234F", "Sender", "User", "Father Name", "Mother Name", null, "MSc", "Address", "City", "State", 123456);
        receiverUser = new User(67890L, 987654321098L, "XYZAB1234G", "Receiver", "User", "Father Name", "Mother Name", null, "BSc", "Address", "City", "State", 654321);

        senderFunds = new Funds();
        senderFunds.setUser(senderUser);
        senderFunds.setBalance(1000L);

        receiverFunds = new Funds();
        receiverFunds.setUser(receiverUser);
        receiverFunds.setBalance(500L);

        transaction = new FundTransfer(12345L, 67890L, 500L);
    }

    @Test
    public void testAddUser_Success() {
        when(userRepo.existsByAadharNumber(senderUser.getAadharNumber())).thenReturn(false);
        when(userRepo.existsByPanNumber(senderUser.getPanNumber())).thenReturn(false);
        when(userRepo.save(any(User.class))).thenReturn(senderUser);

        String result = userService.addUser(senderUser);
        assertTrue(result.contains("Account created successfully"));
    }

    @Test
    public void testAddUser_AadharExists() {
        when(userRepo.existsByAadharNumber(senderUser.getAadharNumber())).thenReturn(true);

        String result = userService.addUser(senderUser);
        assertEquals("Aadhar Number already exists", result);
    }


    @Test
    public void testDeleteUser_UserNotFound() {
        when(userRepo.findById(12345L)).thenReturn(java.util.Optional.empty());

        String result = userService.deleteUser(12345L, senderUser.getAadharNumber());
        assertEquals("Please enter correct account number", result);
    }

    @Test
    public void testTransferFund_Success() {
        when(userRepo.findById(12345L)).thenReturn(java.util.Optional.of(senderUser));
        when(userRepo.findById(67890L)).thenReturn(java.util.Optional.of(receiverUser));
        when(balanceRepo.findByUser(senderUser)).thenReturn(java.util.Optional.of(senderFunds));
        when(balanceRepo.findByUser(receiverUser)).thenReturn(java.util.Optional.of(receiverFunds));
        when(balanceRepo.save(any(Funds.class))).thenReturn(senderFunds);

        String result = userService.transferFund(transaction);
        assertEquals("Transaction done successfully", result);
        assertEquals(500L, senderFunds.getBalance());
        assertEquals(1000L, receiverFunds.getBalance());
    }


    @Test
    public void testAddFund_Success() {
        when(userRepo.findById(12345L)).thenReturn(java.util.Optional.of(senderUser));
        when(balanceRepo.findByUser(senderUser)).thenReturn(java.util.Optional.of(senderFunds));
        when(balanceRepo.save(any(Funds.class))).thenReturn(senderFunds);

        String result = userService.addFund(500L, 12345L);
        assertEquals("Amount credited successfully", result);
        assertEquals(1500L, senderFunds.getBalance());
    }

    @Test
    public void testDebitFund_Success() {
        when(userRepo.findById(12345L)).thenReturn(java.util.Optional.of(senderUser));
        when(balanceRepo.findByUser(senderUser)).thenReturn(java.util.Optional.of(senderFunds));
        when(balanceRepo.save(any(Funds.class))).thenReturn(senderFunds);

        String result = userService.debitFund(500L, 12345L);
        assertEquals("Money debited", result);
        assertEquals(500L, senderFunds.getBalance());
    }

    @Test
    public void testDebitFund_InsufficientBalance() {
        senderFunds.setBalance(200L); // Less than the amount to debit

        when(userRepo.findById(12345L)).thenReturn(java.util.Optional.of(senderUser));
        when(balanceRepo.findByUser(senderUser)).thenReturn(java.util.Optional.of(senderFunds));

        String result = userService.debitFund(500L, 12345L);
        assertEquals("Amount not available", result);
    }

    @Test
    public void testViewFund_Success() {
        when(userRepo.findById(12345L)).thenReturn(java.util.Optional.of(senderUser));
        when(balanceRepo.findByUser(senderUser)).thenReturn(java.util.Optional.of(senderFunds));

        Long result = userService.viewFund(12345L);
        assertEquals(1000L, result);
    }

    @Test
    public void testViewFund_UserNotFound() {
        when(userRepo.findById(12345L)).thenReturn(java.util.Optional.empty());

        Long result = userService.viewFund(12345L);
        assertNull(result);
    }
}

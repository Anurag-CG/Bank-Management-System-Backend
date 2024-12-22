package com.example.userservice.service;

import com.example.userservice.entities.FundTransfer;
import com.example.userservice.entities.Funds;
import com.example.userservice.entities.Login;
import com.example.userservice.entities.User;
import com.example.userservice.repository.BalanceRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.repository.LoginRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class FundServiceTest {

    @Mock
    private BalanceRepository balanceRepo;
    
    @Mock
    private UserRepository userRepo;
    
    @Mock
    private LoginRepository loginRepo;

    @InjectMocks
    private FundService fundService;

    @BeforeEach
    void setUp() {
        // Initializing the mocks and the service
        MockitoAnnotations.openMocks(this);
        fundService = new FundService();
        
        // Setting the dependencies explicitly, if necessary, for testing purposes
        fundService.balanceRepo = balanceRepo;
        fundService.userRepo = userRepo;
        fundService.loginRepo = loginRepo;
    }

    @Test
    void testTransferFund_SuccessfulTransfer() {
        // Given
        User sender = new User();
        sender.setAccountNumber(1L);

        User receiver = new User();
        receiver.setAccountNumber(2L);

        FundTransfer transaction = new FundTransfer(1L, 2L, 100L);

        Funds senderFunds = new Funds();
        senderFunds.setBalance(200L);

        Funds receiverFunds = new Funds();
        receiverFunds.setBalance(100L);

        // Mock the behavior of repositories
        when(userRepo.findById(1L)).thenReturn(java.util.Optional.of(sender));
        when(userRepo.findById(2L)).thenReturn(java.util.Optional.of(receiver));
        when(balanceRepo.findByUser(sender)).thenReturn(java.util.Optional.of(senderFunds));
        when(balanceRepo.findByUser(receiver)).thenReturn(java.util.Optional.of(receiverFunds));

        // Act
        String result = fundService.transferFund(transaction);

        // Assert
        assertEquals("Money transferred successfully", result);
        assertEquals(100L, senderFunds.getBalance());  // Sender's new balance after transfer
        assertEquals(200L, receiverFunds.getBalance()); // Receiver's new balance after transfer
    }

    @Test
    void testTransferFund_InsufficientFunds() {
        // Given
        User sender = new User();
        sender.setAccountNumber(1L);

        User receiver = new User();
        receiver.setAccountNumber(2L);

        FundTransfer transaction = new FundTransfer(1L, 2L, 500L);  // Sender doesn't have enough funds

        Funds senderFunds = new Funds();
        senderFunds.setBalance(200L); // Sender only has 200

        Funds receiverFunds = new Funds();
        receiverFunds.setBalance(100L);

        // Mock the behavior of repositories
        when(userRepo.findById(1L)).thenReturn(java.util.Optional.of(sender));
        when(userRepo.findById(2L)).thenReturn(java.util.Optional.of(receiver));
        when(balanceRepo.findByUser(sender)).thenReturn(java.util.Optional.of(senderFunds));
        when(balanceRepo.findByUser(receiver)).thenReturn(java.util.Optional.of(receiverFunds));

        // Act
        String result = fundService.transferFund(transaction);

        // Assert
        assertEquals("Fund not available", result);  // The transfer should fail due to insufficient funds
    }

    @Test
    void testTransferFund_SenderNotFound() {
        // Given
        User sender = null;  // No sender
        User receiver = new User();
        receiver.setAccountNumber(2L);

        FundTransfer transaction = new FundTransfer(1L, 2L, 100L);

        // Mock the behavior of repositories
        when(userRepo.findById(1L)).thenReturn(java.util.Optional.ofNullable(sender));
        when(userRepo.findById(2L)).thenReturn(java.util.Optional.of(receiver));

        // Act
        String result = fundService.transferFund(transaction);

        // Assert
        assertEquals("Sender not found", result);
    }

    @Test
    void testTransferFund_ReceiverNotFound() {
        // Given
        User sender = new User();
        sender.setAccountNumber(1L);

        User receiver = null;  // No receiver

        FundTransfer transaction = new FundTransfer(1L, 2L, 100L);

        // Mock the behavior of repositories
        when(userRepo.findById(1L)).thenReturn(java.util.Optional.of(sender));
        when(userRepo.findById(2L)).thenReturn(java.util.Optional.ofNullable(receiver));

        // Act
        String result = fundService.transferFund(transaction);

        // Assert
        assertEquals("Receiver not found", result);
    }
    
    @Test
    void testGetBalance() {
        // Given
        User user = new User();
        user.setAccountNumber(1L);

        Login login = new Login();
        login.setUserId("user1");
        login.setUser(user);

        Funds funds = new Funds();
        funds.setBalance(500L);

        // Mock the behavior of repositories
        when(loginRepo.findById("user1")).thenReturn(java.util.Optional.of(login));
        when(balanceRepo.findByUser(user)).thenReturn(java.util.Optional.of(funds));

        // Act
        Long balance = fundService.getBalance("user1");

        // Assert
        assertEquals(500L, balance);  // The balance should be 500
    }

    @Test
    void testGetBalance_UserNotFound() {
        // Given
        User user = new User();
        user.setAccountNumber(1L);

        Login login = new Login();
        login.setUserId("user1");
        login.setUser(user);

        // Mock the behavior of repositories
        when(loginRepo.findById("user1")).thenReturn(java.util.Optional.of(login));
        when(balanceRepo.findByUser(user)).thenReturn(java.util.Optional.empty());  // No funds

        // Act
        Long balance = fundService.getBalance("user1");

        // Assert
        assertEquals(0L, balance);  // The balance should be 0 because no funds exist
    }
}

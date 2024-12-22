package com.example.userservice.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FundTransferTest {

    private FundTransfer fundTransfer;

    @BeforeEach
    void setUp() {
        // Set up a FundTransfer object with initial values
        fundTransfer = new FundTransfer(123456789L, 987654321L, 5000L);
    }

    // Test for constructor success
    @Test
    void testFundTransferConstructor_Success() {
        assertNotNull(fundTransfer, "FundTransfer object should be initialized");
        assertEquals(123456789L, fundTransfer.getSenderAccount(), "Sender account number should be correctly initialized");
        assertEquals(987654321L, fundTransfer.getReceiverAccount(), "Receiver account number should be correctly initialized");
        assertEquals(5000L, fundTransfer.getBalance(), "Balance should be correctly initialized");
    }

    // Test for default constructor success (null values)
    @Test
    void testFundTransferDefaultConstructor_Success() {
        FundTransfer defaultFundTransfer = new FundTransfer();
        assertNull(defaultFundTransfer.getSenderAccount(), "Sender account should be null by default");
        assertNull(defaultFundTransfer.getReceiverAccount(), "Receiver account should be null by default");
        assertNull(defaultFundTransfer.getBalance(), "Balance should be null by default");
    }

    // Test setter and getter for sender account - success
    @Test
    void testSetSenderAccount_Success() {
        fundTransfer.setSenderAccount(111111111L);
        assertEquals(111111111L, fundTransfer.getSenderAccount(), "Sender account should be updated correctly");
    }

    // Test setter and getter for receiver account - success
    @Test
    void testSetReceiverAccount_Success() {
        fundTransfer.setReceiverAccount(222222222L);
        assertEquals(222222222L, fundTransfer.getReceiverAccount(), "Receiver account should be updated correctly");
    }

    // Test setter and getter for balance - success
    @Test
    void testSetBalance_Success() {
        fundTransfer.setBalance(10000L);
        assertEquals(10000L, fundTransfer.getBalance(), "Balance should be updated correctly");
    }

    // Test setter and getter for sender account - failure
    @Test
    void testSetSenderAccount_Failure() {
        fundTransfer.setSenderAccount(111111111L);
        assertNotEquals(123456789L, fundTransfer.getSenderAccount(), "Sender account should not be the original value after updating");
    }

    // Test setter and getter for receiver account - failure
    @Test
    void testSetReceiverAccount_Failure() {
        fundTransfer.setReceiverAccount(222222222L);
        assertNotEquals(987654321L, fundTransfer.getReceiverAccount(), "Receiver account should not be the original value after updating");
    }

    // Test setter and getter for balance - failure
    @Test
    void testSetBalance_Failure() {
        fundTransfer.setBalance(10000L);
        assertNotEquals(5000L, fundTransfer.getBalance(), "Balance should not be the original value after updating");
    }
}

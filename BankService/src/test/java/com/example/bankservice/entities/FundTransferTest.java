package com.example.bankservice.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FundTransferTest {

    private FundTransfer fundTransfer;

    @BeforeEach
    public void setUp() {
        // Initialize a FundTransfer object with sample data
        fundTransfer = new FundTransfer(12345L, 67890L, 1000L);
    }

    // Test constructor and getter methods
    @Test
    public void testConstructorAndGetterMethods() {
        assertNotNull(fundTransfer, "FundTransfer object should not be null");
        assertEquals(12345L, fundTransfer.getSenderAccount(), "Sender account number should match");
        assertEquals(67890L, fundTransfer.getReceiverAccount(), "Receiver account number should match");
        assertEquals(1000L, fundTransfer.getBalance(), "Balance should match");
    }

    // Test setter methods
    @Test
    public void testSetterMethods() {
        fundTransfer.setSenderAccount(54321L);
        fundTransfer.setReceiverAccount(98765L);
        fundTransfer.setBalance(2000L);

        assertEquals(54321L, fundTransfer.getSenderAccount(), "Sender account number should match after set");
        assertEquals(98765L, fundTransfer.getReceiverAccount(), "Receiver account number should match after set");
        assertEquals(2000L, fundTransfer.getBalance(), "Balance should match after set");
    }

    // Success Scenario: Test equality of two FundTransfer objects (with same values)
    @Test
    public void testEquality_Success() {
        FundTransfer fundTransfer2 = new FundTransfer(12345L, 67890L, 1000L);

        // Check if two FundTransfer objects with the same attributes are equal (assuming proper implementation of equals in FundTransfer)
        assertEquals(fundTransfer.getSenderAccount(), fundTransfer2.getSenderAccount(), "Sender account numbers should match");
        assertEquals(fundTransfer.getReceiverAccount(), fundTransfer2.getReceiverAccount(), "Receiver account numbers should match");
        assertEquals(fundTransfer.getBalance(), fundTransfer2.getBalance(), "Balance should match");
    }

    // Failure Scenario: Test inequality of two FundTransfer objects with different values
    @Test
    public void testEquality_Failure() {
        FundTransfer fundTransfer2 = new FundTransfer(54321L, 98765L, 2000L);

        // Check if two FundTransfer objects with different attributes are not equal
        assertNotEquals(fundTransfer.getSenderAccount(), fundTransfer2.getSenderAccount(), "Sender account numbers should not match");
        assertNotEquals(fundTransfer.getReceiverAccount(), fundTransfer2.getReceiverAccount(), "Receiver account numbers should not match");
        assertNotEquals(fundTransfer.getBalance(), fundTransfer2.getBalance(), "Balances should not match");
    }
}

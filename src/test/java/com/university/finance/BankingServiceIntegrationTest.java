package com.university.finance;

import com.university.finance.model.*;
import com.university.finance.service.BankingService;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BankingServiceIntegrationTest {
    private BankingService service;

    @BeforeEach
    void setUp() {
        service = new BankingService();
    }

    @Test
    void fullBankingFlowShouldWork() {
        User user = service.createUser("integration", "test123");
        Account acc1 = service.createAccount("integration", 1000);
        Account acc2 = service.createAccount("integration", 0);

        service.deposit(acc1.getAccountNumber(), 500);
        service.transfer(acc1.getAccountNumber(), acc2.getAccountNumber(), 300);

        assertEquals(1200.0, acc1.getBalance());
        assertEquals(300.0, acc2.getBalance());
    }
}
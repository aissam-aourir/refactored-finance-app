package com.university.finance;

import com.university.finance.model.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private User user;
    private Account account;

    @BeforeEach
    void setUp() {
        user = new User("test", "pass");
        account = new Account(user, 1000.0);
    }

    @Test
    void depositShouldIncreaseBalance() {
        account.deposit(500);
        assertEquals(1500.0, account.getBalance(), 0.01);
    }

    @Test
    void withdrawInsufficientFundsShouldFail() {
        assertFalse(account.withdraw(2000));
        assertEquals(1000.0, account.getBalance(), 0.01);
    }

    @Test
    void transferShouldWork() {
        Account dest = new Account(user, 0);
        account.transferTo(dest, 400);
        assertEquals(600.0, account.getBalance(), 0.01);
        assertEquals(400.0, dest.getBalance(), 0.01);
    }

    @Test
    void accountNumberShouldBeUnique() {
        Account acc2 = new Account(user, 500);
        assertNotEquals(account.getAccountNumber(), acc2.getAccountNumber());
    }
}
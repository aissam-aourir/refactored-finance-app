package com.university.finance;

import com.university.finance.model.*;
import com.university.finance.pattern.strategy.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class TransactionStrategyTest {
    private User user1, user2;
    private Account acc1, acc2;

    @BeforeEach
    void setUp() {
        user1 = new User("ali", "pass");
        user2 = new User("mohamed", "pass");
        acc1 = new Account(user1, 1000);
        acc2 = new Account(user2, 100);
    }

    @Test
    void depositStrategyShouldAddMoney() {
        new DepositStrategy().execute(null, acc1, 500);
        assertEquals(1500.0, acc1.getBalance());
    }

    @Test
    void withdrawStrategyShouldRemoveMoney() {
        new WithdrawStrategy().execute(acc1, null, 300);
        assertEquals(700.0, acc1.getBalance());
    }

    @Test
    void transferStrategyShouldMoveMoney() {
        new TransferStrategy().execute(acc1, acc2, 200);
        assertEquals(800.0, acc1.getBalance());
        assertEquals(300.0, acc2.getBalance());
        assertEquals(1, acc1.getTransactionHistory().size());
        assertEquals(1, acc2.getTransactionHistory().size());
    }
}
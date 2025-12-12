package com.university.finance;

import com.university.finance.model.Account;
import com.university.finance.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void testUserCreation() {
        User user = new User("mohamed", "123456");
        assertEquals("mohamed", user.getUsername());
        assertEquals("123456", user.getPassword());
    }

    @Test
    void testAddAccount() {
        User user = new User("ali", "pass");
        Account acc = new Account(user, 1000);
        assertEquals(1, user.getAccounts().size());
    }
}
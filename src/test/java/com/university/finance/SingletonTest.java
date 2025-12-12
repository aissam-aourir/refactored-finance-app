package com.university.finance;

import com.university.finance.model.AccountNumberGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SingletonTest {
    @Test
    void singletonShouldReturnSameInstance() {
        var gen1 = AccountNumberGenerator.getInstance();
        var gen2 = AccountNumberGenerator.getInstance();
        assertSame(gen1, gen2);
    }

    @Test
    void accountNumbersShouldBeUnique() {
        var gen = AccountNumberGenerator.getInstance();
        String n1 = gen.generateUniqueAccountNumber();
        String n2 = gen.generateUniqueAccountNumber();
        assertNotEquals(n1, n2);
    }
}
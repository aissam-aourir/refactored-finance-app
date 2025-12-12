package com.university.finance.model;

import java.util.HashSet;
import java.util.Set;

public class AccountNumberGenerator {
    private static AccountNumberGenerator instance;
    private final Set<String> usedNumbers=new HashSet<>();
    private int counter=1;//voir combien de compte crees
    private AccountNumberGenerator() {}
    public static synchronized AccountNumberGenerator getInstance() {
        if(instance==null) {
            instance=new AccountNumberGenerator();
        }
        return instance;
    }
    public synchronized String generateUniqueAccountNumber() {
        String number;
        do {
            number = String.format("ACC-%06d", counter++);
        } while (usedNumbers.contains(number));
        usedNumbers.add(number);
        return number;
    }
    public Set<String> getUsedNumbers() {
        return new HashSet<>(usedNumbers);
    }
}

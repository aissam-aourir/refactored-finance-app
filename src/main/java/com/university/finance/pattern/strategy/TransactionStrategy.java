package com.university.finance.pattern.strategy;

import com.university.finance.model.Account;
import com.university.finance.model.Transaction;

public interface TransactionStrategy {
    boolean execute(Account source, Account destination, double amount);
    Transaction createSourceTransaction(double amount);
    Transaction createDestinationTransaction(double amount);
}

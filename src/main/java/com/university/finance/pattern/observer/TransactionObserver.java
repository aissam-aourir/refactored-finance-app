package com.university.finance.pattern.observer;

import com.university.finance.model.Account;
import com.university.finance.model.Transaction;

public interface TransactionObserver {
    boolean onTransaction(Account sourceAccount, Account destinationAccount,
                       Transaction sourceTransaction, Transaction destinationTransaction);
}

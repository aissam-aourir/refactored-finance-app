package com.university.finance.pattern.observer;

import com.university.finance.model.Account;
import com.university.finance.model.Transaction;

public class NotificationService implements TransactionObserver{
    @Override
    public boolean onTransaction(Account source, Account destination,
                              Transaction srcTx, Transaction destTx) {

        if (srcTx != null && source != null) {
            System.out.println("[NOTIF → " + source.getOwner().getUsername() + "] " + srcTx);
            return true;
        }
        if (destTx != null && destination != null) {
            System.out.println("[NOTIF → " + destination.getOwner().getUsername() + "] " + destTx);
            return true;
        }
        return false;
    }
}

package com.university.finance.pattern.observer;

import com.university.finance.model.Account;
import com.university.finance.model.Transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditLogger implements TransactionObserver{
    @Override
    public boolean onTransaction(Account source, Account destination,
                              Transaction srcTx, Transaction destTx) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        StringBuilder log = new StringBuilder("[AUDIT] ").append(time);

        if (srcTx != null) {
            log.append(" | ").append(source.getOwner().getUsername())
                    .append(" : ").append(srcTx);
            return false;
        }
        if (destTx != null) {
            log.append(" | ").append(destination.getOwner().getUsername())
                    .append(" : ").append(destTx);
            return false;
        }

        System.out.println(log);
        // En vrai projet : écrire dans un fichier log fichier ou base de données
        return  true;
    }
}

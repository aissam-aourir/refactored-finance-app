package com.university.finance.service;

import com.university.finance.model.*;
import com.university.finance.pattern.observer.TransactionObserver;
import com.university.finance.pattern.strategy.TransactionStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionService {
    private final List<TransactionObserver> observers = new ArrayList<>();

    public void addObserver(TransactionObserver observer) {
        observers.add(observer);
    }

    public void executeTransaction(TransactionStrategy strategy,
                                   Account source, Account destination,
                                   double amount) {

        // 1. Exécute la stratégie (dépôt, retrait, virement)
        strategy.execute(source, destination, amount);

        // 2. Crée les transactions pour l'historique
        Transaction srcTx = strategy.createSourceTransaction(amount);
        Transaction destTx = strategy.createDestinationTransaction(amount);

        if (srcTx != null && source != null) {
            source.addTransaction(srcTx);
        }
        if (destTx != null && destination != null) {
            destination.addTransaction(destTx);
        }

        // 3. NOTIFIE TOUS LES OBSERVATEURS (audit + notification)
        for (TransactionObserver observer : observers) {
            observer.onTransaction(source, destination, srcTx, destTx);
        }
    }
}
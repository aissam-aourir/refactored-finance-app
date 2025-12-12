package com.university.finance.pattern.factory;

import com.university.finance.model.Account;
import com.university.finance.model.User;

public class AccountFactory {
    public static Account createAccount(User owner, double balance){
        if (owner == null) {
            throw new IllegalArgumentException("L'utilisateur propriétaire est requis");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Le solde initial ne peut pas être négatif");
        }
        // Le numéro unique est généré automatiquement via le Singleton
        return new Account(owner, balance);
    }
    public static Account createEmptyAccount(User owner) {
        return createAccount(owner, 0.0);
    }
}

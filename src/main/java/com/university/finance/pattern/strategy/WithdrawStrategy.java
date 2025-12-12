package com.university.finance.pattern.strategy;

import com.university.finance.model.Account;
import com.university.finance.model.Transaction;
import com.university.finance.model.TypeTransaction;

public class WithdrawStrategy implements  TransactionStrategy {

    @Override
    public boolean execute(Account source, Account destination, double amount){
        return source.withdraw(amount);
    }
    @Override
    public Transaction createSourceTransaction(double amount){
        return new Transaction(TypeTransaction.WITHDRAWAL, amount);
    }
    @Override
    public Transaction createDestinationTransaction(double amount){
        return null;//pas de destination
    }

}

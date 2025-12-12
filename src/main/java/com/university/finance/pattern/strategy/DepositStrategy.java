package com.university.finance.pattern.strategy;

import com.university.finance.model.Account;
import com.university.finance.model.Transaction;
import com.university.finance.model.TypeTransaction;

public class DepositStrategy implements  TransactionStrategy{
    @Override
    public boolean execute(Account source, Account destination, double amount){
        return destination.deposit(amount);
    }
    @Override
    public Transaction createSourceTransaction(double amount){
        return null;//car il y a pas de source en cas de deposit
    }
    @Override
    public Transaction createDestinationTransaction(double amount){
        return new Transaction(TypeTransaction.DEPOSIT, amount);
    }

}
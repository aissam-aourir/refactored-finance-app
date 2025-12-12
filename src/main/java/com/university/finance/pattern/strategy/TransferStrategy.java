package com.university.finance.pattern.strategy;

import com.university.finance.model.Account;
import com.university.finance.model.Transaction;
import com.university.finance.model.TypeTransaction;

public class TransferStrategy implements TransactionStrategy{
    @Override
    public boolean execute(Account source, Account destination, double amount){
        if(source.withdraw(amount)){
            return destination.deposit(amount);
        }
        return false;
    }
    @Override
    public Transaction createSourceTransaction(double amount){
        return new Transaction(TypeTransaction.TRANSFER_OUT,amount);
    }
    @Override
    public Transaction createDestinationTransaction(double amount){
        return new Transaction(TypeTransaction.TRANSFER_IN,amount);
    }
}

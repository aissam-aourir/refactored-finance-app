package com.university.finance.model;

import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.*;
import lombok.ToString;

@Data
@Getter
@ToString
public class Account {
    private final String accountNumber;
    private final User owner;
    private double balance;
    private final List<Transaction> transactionHistory=new ArrayList<>();

    public Account(User owner,double balance) {
        this.accountNumber=AccountNumberGenerator.getInstance().generateUniqueAccountNumber();
        this.owner = owner;
        this.balance = balance;
        this.owner.addAccount(this);//pour faire la laison automatique avec les comptes de l'utilisateur
    }
    public boolean deposit(double amount){
        if(amount<=0){
            return false;
        }
        this.balance += amount;
        return true;
    }
    public boolean withdraw(double amount){
        if(amount<=0){
            return false;
        }
        if(this.balance < amount){
            System.out.println("LE SOLDE EST INSUFFISANT ");
            return false;
        }else{
            System.out.println("ON A RETIRE JUSTEMENT "+amount);
            this.balance -= amount;
            return true;
        }
    }
    public boolean transferTo(Account destination, double amount){
        if(amount<=0){
            return false;
        }
        if(this.withdraw(amount)){
            boolean deposited=destination.deposit(amount);
            if(deposited!=true){
                this.balance+=amount;
                return false;
            }
            return true;
        }else{
            return false;
        }
    }
    public boolean addTransaction(Transaction transaction){
        return this.transactionHistory.add(transaction);
    }
    public List<Transaction> getTransactionHistory(){
        return Collections.unmodifiableList(this.transactionHistory);
    }
    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", owner=" + owner.getUsername() +
                ", balance=" + balance +
                '}';
    }
}

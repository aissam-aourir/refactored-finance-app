package com.university.finance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {
    private LocalDateTime date=LocalDateTime.now();
    private TypeTransaction typeTransaction;
    private double amount;
    private String relatedAccount;
    public Transaction(TypeTransaction typeTransaction, double amount){
         this.typeTransaction=typeTransaction;
            this.amount=amount;
            this.relatedAccount=null;
     }
    @Override
    public String toString() {
        return String.format("%s | %s | %+.2f | %s",
                date.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                typeTransaction,
                amount,
                relatedAccount != null ? "→ " + relatedAccount : ""
        );
    }
}

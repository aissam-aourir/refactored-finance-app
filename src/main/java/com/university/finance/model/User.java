package com.university.finance.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private String username;
    private String password;
    private final List<Account> accounts= new ArrayList<>();
    public boolean addAccount(Account account) {
        System.out.println("Adding account for user: " + this.username);
        return accounts.add(account);
    }
    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}

package com.university.finance.pattern.factory;

import com.university.finance.model.User;

public class UserFactory {
    public static User createUser(String username , String password){
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom d'utilisateur est obligatoire");
        }
        if (password == null || password.length() < 4) {
            throw new IllegalArgumentException("Le mot de passe doit faire au moins 4 caractères");
        }
        return new User(username.trim(), password);
    }

}

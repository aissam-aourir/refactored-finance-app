package com.university.finance.service;

import com.university.finance.model.*;
import com.university.finance.pattern.factory.*;
import com.university.finance.pattern.observer.AuditLogger;
import com.university.finance.pattern.observer.NotificationService;
import com.university.finance.pattern.strategy.*;

import java.util.*;

import static com.university.finance.MainApp.scanner;


/**
 * Service principal de la banque
 * Orchestre TOUT : création users/comptes, exécution transactions, notifications
 * Utilise les 3 patterns + Singleton
 */
public class BankingService {
    private final Map<String, User> users = new HashMap<>(); // username → User
    private final TransactionService transactionService;

    public BankingService() {
        this.transactionService = new TransactionService();
        // On ajoute les observateurs une seule fois au démarrage
        transactionService.addObserver(new AuditLogger());
        transactionService.addObserver(new NotificationService());
    }

    // ======================
    // Création d'utilisateur
    // ======================
    public User createUser(String username, String password) {
        if (users.containsKey(username)) {
            throw new IllegalArgumentException("Utilisateur déjà existant : " + username);
        }
        User user = UserFactory.createUser(username, password);
        users.put(username, user);
        System.out.println("Utilisateur créé : " + username);
        return user;
    }

    // ======================
    // Création de compte
    // ======================
    public Account createAccount(String username, double initialBalance) {
        User user = users.get(username);
        if (user == null) {
            throw new IllegalArgumentException("Utilisateur inconnu : " + username);
        }
        Account account = AccountFactory.createAccount(user, initialBalance);
        System.out.println("Compte créé : " + account.getAccountNumber() + " | Solde : " + initialBalance);
        return account;
    }

    // ======================
    // Opérations bancaires
    // ======================
    public void deposit(String accountNumber, double amount) {
        if (!verifyOwnership(accountNumber)) {
            return;
        }
        Account account = findAccountByNumber(accountNumber);
//        Account account = findAccountByNumber(accountNumber);
        transactionService.executeTransaction(
                new DepositStrategy(),
                null,
                account,
                amount
        );
    }

    public void withdraw(String accountNumber, double amount) {
        if (!verifyOwnership(accountNumber)) {
            return;
        }
        Account account = findAccountByNumber(accountNumber);
//        Account account = findAccountByNumber(accountNumber);
        transactionService.executeTransaction(
                new WithdrawStrategy(),
                account,
                null,
                amount
        );
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        if (!verifyOwnership(fromAccountNumber)) {
            return;
        }
        Account source = findAccountByNumber(fromAccountNumber);
//        Account source = findAccountByNumber(fromAccountNumber);
        Account destination = findAccountByNumber(toAccountNumber);

        if (source.getOwner().equals(destination.getOwner())) {
            System.out.println("Transfert interne entre comptes du même utilisateur");
        }

        transactionService.executeTransaction(
                new TransferStrategy(),
                source,
                destination,
                amount
        );
    }

    // ======================
    // Consultation
    // ======================
    public void showBalance(String accountNumber) {
        if (!verifyOwnership(accountNumber)) {
            return;
        }
        Account account = findAccountByNumber(accountNumber);
//        Account account = findAccountByNumber(accountNumber);
        System.out.println("Solde du compte " + accountNumber + " : " + account.getBalance() + " DH");
    }

    public void showHistory(String accountNumber) {
        if (!verifyOwnership(accountNumber)) {
            return;
        }
        Account account = findAccountByNumber(accountNumber);
//        Account account = findAccountByNumber(accountNumber);
        System.out.println("\n=== Historique du compte " + accountNumber + " ===");
        account.getTransactionHistory().forEach(System.out::println);
        System.out.println("=================================\n");
    }

    // ======================
    // Utilitaires
    // ======================
    private Account findAccountByNumber(String accountNumber) {
        return users.values().stream()
                .flatMap(u -> u.getAccounts().stream())
                .filter(a -> a.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Compte introuvable : " + accountNumber));
    }

    public List<Account> getAllAccounts() {
        List<Account> all = new ArrayList<>();
        users.values().forEach(u -> all.addAll(u.getAccounts()));
        return Collections.unmodifiableList(all);
    }

    public User getUser(String username) {
        return users.get(username);
    }

    private boolean verifyOwnership(String accountNumber) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Vérification sécurité - Entrez votre username : ");
        String username = scanner.nextLine();
        System.out.print("Vérification sécurité - Entrez votre mot de passe : ");
        String password = scanner.nextLine();

        Account account = findAccountByNumber(accountNumber);
        User owner = account.getOwner();
        if (owner.getUsername().equals(username) && owner.getPassword().equals(password)) {
            return true;
        } else {
            System.out.println("Erreur : Identifiants incorrects. Transaction annulée.");
            return false;
        }
    }

    // AJOUTE CETTE MÉTHODE DANS BankingService.java
    public void showUserAccounts() {
        {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Username : ");
            String username = scanner.nextLine();
            System.out.print("Mot de passe : ");
            String password = scanner.nextLine();

            User user = users.get(username);
            if (user == null || !user.getPassword().equals(password)) {
                System.out.println("Identifiants incorrects !");
                return;
            }

            List<Account> accounts = user.getAccounts();
            if (accounts.isEmpty()) {
                System.out.println("Aucun compte trouvé pour " + username);
                return;
            }

            System.out.println("\n=== COMPTES DE " + username.toUpperCase() + " ===");
            for (Account acc : accounts) {
                System.out.println("• Compte : " + acc.getAccountNumber() +
                        " | Solde : " + acc.getBalance() + " DH");
            }
            System.out.println("=============================================\n");
        }
    }
}
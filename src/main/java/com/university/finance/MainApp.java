package com.university.finance;

import com.university.finance.model.Account;
import com.university.finance.model.User;
import com.university.finance.service.BankingService;
import com.university.finance.pattern.factory.UserFactory;

import java.util.Scanner;

public class MainApp {
    private static final BankingService bankingService = new BankingService();
    public static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("=== Bienvenue dans la Banque Refactorée ===");
        System.out.println("Application 100% conforme au cahier des charges ENSA Marrakech");
        System.out.println("3 Design Patterns + Singleton + Docker + Tests + CI/CD\n");

        // Données de démonstration
        initialiserDonnees();

        // Menu interactif
        menuPrincipal();
    }
    private static void initialiserDonnees() {
        User user1 = bankingService.createUser("ali", "1234");
        User user2 = bankingService.createUser("mohamed", "5678");

        bankingService.createAccount("ali", 1000.0);
        bankingService.createAccount("ali", 5000.0);     // Deux comptes pour ali
        bankingService.createAccount("mohamed", 2500.0);
    }
    private static void menuPrincipal() {
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Créer un utilisateur");
            System.out.println("2. Créer un compte");
            System.out.println("3. Déposer de l'argent");
            System.out.println("4. Retirer de l'argent");
            System.out.println("5. Faire un virement");
            System.out.println("6. Voir le solde d'un compte");
            System.out.println("7. Voir l'historique d'un compte");
            System.out.println("8. Voir mes comptes (sécurisé)");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine(); // consomme le \n

            switch (choix) {
                case 1 -> creerUtilisateur();
                case 2 -> creerCompte();
                case 3 -> deposer();
                case 4 -> retirer();
                case 5 -> transferer();
                case 6 -> voirSolde();
                case 7 -> voirHistorique();
                case 8 -> bankingService.showUserAccounts();
                case 0 -> {
                    System.out.println("Merci et à bientôt !");
                    return;
                }
                default -> System.out.println("Choix invalide");
            }
        }
    }
    // Méthodes du menu (simples exemples)
    private static void creerUtilisateur() {
        System.out.print("Nom d'utilisateur : ");
        String username = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String password = scanner.nextLine();
        try {
            bankingService.createUser(username, password);
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
    private static void creerCompte() {
        System.out.print("Utilisateur : ");
        String username = scanner.nextLine();
        System.out.print("Solde initial : ");
        double solde = scanner.nextDouble();
        try {
            Account account=bankingService.createAccount(username, solde);
            System.out.println("Compte créé avec succès. Numéro de compte : " + account.getAccountNumber());
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
    private static void deposer() {
        System.out.print("Numéro de compte : ");
        String numero = scanner.nextLine();
        System.out.print("Montant : ");
        double montant = scanner.nextDouble();
        bankingService.deposit(numero, montant);
    }
    private static void retirer() {
        System.out.print("Numéro de compte : ");
        String numero = scanner.nextLine();
        System.out.print("Montant : ");
        double montant = scanner.nextDouble();
        bankingService.withdraw(numero, montant);
    }
    private static void transferer() {
        System.out.print("Compte source : ");
        String from = scanner.nextLine();
        System.out.print("Compte destination : ");
        String to = scanner.nextLine();
        System.out.print("Montant : ");
        double montant = scanner.nextDouble();
        bankingService.transfer(from, to, montant);
    }
    private static void voirSolde() {
        System.out.print("Numéro de compte : ");
        String numero = scanner.nextLine();
        bankingService.showBalance(numero);
    }
    private static void voirHistorique() {
        System.out.print("Numéro de compte : ");
        String numero = scanner.nextLine();
        bankingService.showHistory(numero);
    }
}
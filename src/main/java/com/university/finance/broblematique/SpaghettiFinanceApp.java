package com.university.finance.broblematique;
import java.util.*;
import java.text.SimpleDateFormat;
public class SpaghettiFinanceApp {
    // Tout dans une seule classe monolithique
    static Map<String, Double> accounts = new HashMap<>();
    static Map<String, List<String>> transactions = new HashMap<>();
    static Map<String, String> users = new HashMap<>();
    
    public static void main(String[] args) {
        // Initialisation chaotique
        accounts.put("user1", 1000.0);
        accounts.put("user2", 500.0);
        users.put("user1", "password1");
        users.put("user2", "password2");
        
        // Interface en ligne de commande primitive
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            System.out.println("=== Système Bancaire Spaghetti ===");
            System.out.println("1. Afficher solde");
            System.out.println("2. Déposer argent");
            System.out.println("3. Retirer argent");
            System.out.println("4. Transfert");
            System.out.println("5. Historique");
            System.out.println("6. Ajouter utilisateur");
            System.out.println("0. Quitter");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.println("Nom utilisateur:");
                    String user = scanner.next();
                    if (accounts.containsKey(user)) {
                        System.out.println("Solde: " + accounts.get(user));
                    } else {
                        System.out.println("Utilisateur non trouvé!");
                    }
                    break;
                    
                case 2:
                    System.out.println("Utilisateur:");
                    String depositUser = scanner.next();
                    System.out.println("Montant:");
                    double amount = scanner.nextDouble();
                    
                    if (accounts.containsKey(depositUser)) {
                        double current = accounts.get(depositUser);
                        accounts.put(depositUser, current + amount);
                        
                        // Journalisation inconsistante
                        String log = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) 
                            + " Dépôt: +" + amount;
                        if (!transactions.containsKey(depositUser)) {
                            transactions.put(depositUser, new ArrayList<>());
                        }
                        transactions.get(depositUser).add(log);
                        
                        System.out.println("Dépôt réussi!");
                    }
                    break;
                    
                case 3:
                    System.out.println("Utilisateur:");
                    String withdrawUser = scanner.next();
                    System.out.println("Montant:");
                    double withdrawAmount = scanner.nextDouble();
                    
                    if (accounts.containsKey(withdrawUser)) {
                        double current = accounts.get(withdrawUser);
                        if (current >= withdrawAmount) {
                            accounts.put(withdrawUser, current - withdrawAmount);
                            
                            // Duplication de code
                            String log = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) 
                                + " Retrait: -" + withdrawAmount;
                            if (!transactions.containsKey(withdrawUser)) {
                                transactions.put(withdrawUser, new ArrayList<>());
                            }
                            transactions.get(withdrawUser).add(log);
                        }
                    }
                    break;
                    
                case 4:
                    // Méthode trop longue
                    System.out.println("De l'utilisateur:");
                    String fromUser = scanner.next();
                    System.out.println("Vers utilisateur:");
                    String toUser = scanner.next();
                    System.out.println("Montant:");
                    double transferAmount = scanner.nextDouble();
                    
                    if (accounts.containsKey(fromUser) && accounts.containsKey(toUser)) {
                        double fromBalance = accounts.get(fromUser);
                        if (fromBalance >= transferAmount) {
                            accounts.put(fromUser, fromBalance - transferAmount);
                            accounts.put(toUser, accounts.get(toUser) + transferAmount);
                            
                            // Journalisation répétée
                            String logFrom = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) 
                                + " Transfert à " + toUser + ": -" + transferAmount;
                            String logTo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) 
                                + " Transfert de " + fromUser + ": +" + transferAmount;
                            
                            if (!transactions.containsKey(fromUser)) {
                                transactions.put(fromUser, new ArrayList<>());
                            }
                            if (!transactions.containsKey(toUser)) {
                                transactions.put(toUser, new ArrayList<>());
                            }
                            
                            transactions.get(fromUser).add(logFrom);
                            transactions.get(toUser).add(logTo);
                        }
                    }
                    break;
                    
                case 5:
                    System.out.println("Utilisateur:");
                    String historyUser = scanner.next();
                    if (transactions.containsKey(historyUser)) {
                        for (String t : transactions.get(historyUser)) {
                            System.out.println(t);
                        }
                    }
                    break;
                    
                case 6:
                    System.out.println("Nouvel utilisateur:");
                    String newUser = scanner.next();
                    System.out.println("Mot de passe:");
                    String password = scanner.next();
                    System.out.println("Dépôt initial:");
                    double initialDeposit = scanner.nextDouble();
                    
                    users.put(newUser, password);
                    accounts.put(newUser, initialDeposit);
                    break;
                    
                case 0:
                    running = false;
                    break;
            }
        }
        scanner.close();
    }
}

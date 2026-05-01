package com.pluralsight.ui;

import com.pluralsight.model.Transaction;
import com.pluralsight.service.TransactionFileRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class LedgerApp {
    private final Scanner scanner = new Scanner(System.in);
    private final TransactionFileRepository repository = new TransactionFileRepository();

    public void run() {
        boolean running = true;

        while (running) {
            showHomeScreen();
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D" -> addDeposit();
                case "P" -> makePayment();
                case "L" -> showLedgerScreen();
                case "X" -> {
                    running = false;
                    System.out.println("Closing Sephora Ledger...");
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void showHomeScreen() {
        System.out.println();
        System.out.println("========== Sephora Ledger ==========");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
        System.out.print("Choose an option: ");
    }

    private void addDeposit() {
        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        amount = Math.abs(amount);

        Transaction transaction = new Transaction(
                LocalDate.now(),
                LocalTime.now().withNano(0),
                description,
                vendor,
                amount
        );

        repository.addTransaction(transaction);
        System.out.println("Deposit saved.");
    }

    private void makePayment() {
        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        amount = -Math.abs(amount);

        Transaction transaction = new Transaction(
                LocalDate.now(),
                LocalTime.now().withNano(0),
                description,
                vendor,
                amount
        );

        repository.addTransaction(transaction);
        System.out.println("Payment saved.");
    }

    private void showLedgerScreen() {
        boolean inLedger = true;

        while (inLedger) {
            System.out.println();
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("H) Home");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A" -> displayTransactions(repository.getAllTransactions());
                case "D" -> displayDeposits();
                case "P" -> displayPayments();
                case "H" -> inLedger = false;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void displayDeposits() {
        List<Transaction> transactions = repository.getAllTransactions();

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                System.out.println(transaction);
            }
        }
    }

    private void displayPayments() {
        List<Transaction> transactions = repository.getAllTransactions();

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                System.out.println(transaction);
            }
        }
    }

    private void displayTransactions(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}

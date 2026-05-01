package com.pluralsight.ui;

import java.util.Scanner;

public class LedgerApp {
    private final Scanner scanner = new Scanner(System.in);

     public  void run() {
        boolean running = true;

        while (running) {
            showHomeScreen();
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    System.out.println("Add Deposit coming soon...");
                    break;
                case "P":
                    System.out.println("Make Payment coming soon...");
                    break;
                case "L":
                    System.out.println("Ledger screen coming soon...");
                    break;
                case "X":
                    running = false;
                    System.out.println("Closing Sephora Ledger...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void showHomeScreen() {
        System.out.println();
        System.out.println("====================================");
        System.out.println("         Sephora Ledger");
        System.out.println(" Beauty Sales and Expense Tracker");
        System.out.println("====================================");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment (Debit)");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
        System.out.print("Choose an option: ");
    }
}


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
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void showHomeScreen() {
        System.out.println();
        System.out.println("💄 =============================== 💄");
        System.out.println("        ✨ SEPHORA LEDGER ✨        ");
        System.out.println("      💰 Accounting Ledger App 💰   ");
        System.out.println("💄 =============================== 💄");
        System.out.println();
        System.out.println("  💵  D) Add Deposit");
        System.out.println("  💸  P) Make Payment");
        System.out.println("  📒  L) Ledger");
        System.out.println("  🚪  X) Exit");
        System.out.println();
        System.out.print("👉 Choose an option: ");
    }

    private void addDeposit() {
        System.out.print("Description: ");
        String description = scanner.nextLine().trim();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine().trim();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().withNano(0);

        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        repository.addTransaction(transaction);
        System.out.println("Your Deposit Has Been Saved.");
    }

    private void makePayment() {
        System.out.print("Description: ");
        String description = scanner.nextLine().trim();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine().trim();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().withNano(0);

        Transaction transaction = new Transaction(date, time, description, vendor, -amount);
        repository.addTransaction(transaction);
        System.out.println("Your Payment Has Been Saved...");
    }

    private void showLedgerScreen() {
        boolean inLedger = true;

        while (inLedger) {
            System.out.println();
            System.out.println("✨ ======= LEDGER ======= ✨");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("👉 Choose an option: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A" -> displayTransactions(repository.getAllTransactions());
                case "D" -> displayDeposits();
                case "P" -> displayPayments();
                case "R" -> showReportsScreen();
                case "H" -> inLedger = false;
                default -> System.out.println("Invalid option. Please try again...");
            }
        }
    }

    private void showReportsScreen() {
        boolean inReports = true;

        while (inReports) {
            System.out.println();
            System.out.println("📊 ======= REPORTS ======= 📊");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.print("👉 Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> displayTransactions(filterMonthToDate());
                case "2" -> displayTransactions(filterPreviousMonth());
                case "3" -> displayTransactions(filterYearToDate());
                case "4" -> displayTransactions(filterPreviousYear());
                case "5" -> searchByVendor();
                case "0" -> inReports = false;
                default -> System.out.println("❌ Invalid option. Please try again.");
            }
        }
    }

    private List<Transaction> filterMonthToDate() {
        LocalDate now = LocalDate.now();
        return repository.getAllTransactions().stream()
                .filter(t -> !t.getDate().isBefore(now.withDayOfMonth(1)))
                .toList();
    }

    private List<Transaction> filterPreviousMonth() {
        LocalDate now = LocalDate.now();
        LocalDate firstOfPrevMonth = now.minusMonths(1).withDayOfMonth(1);
        LocalDate lastOfPrevMonth = now.withDayOfMonth(1).minusDays(1);
        return repository.getAllTransactions().stream()
                .filter(t -> !t.getDate().isBefore(firstOfPrevMonth) && !t.getDate().isAfter(lastOfPrevMonth))
                .toList();
    }

    private List<Transaction> filterYearToDate() {
        LocalDate now = LocalDate.now();
        return repository.getAllTransactions().stream()
                .filter(t -> !t.getDate().isBefore(now.withDayOfYear(1)))
                .toList();
    }

    private List<Transaction> filterPreviousYear() {
        int prevYear = LocalDate.now().getYear() - 1;
        return repository.getAllTransactions().stream()
                .filter(t -> t.getDate().getYear() == prevYear)
                .toList();
    }

    private void searchByVendor() {
        System.out.print("Enter vendor name: ");
        String vendor = scanner.nextLine().trim().toLowerCase();
        List<Transaction> results = repository.getAllTransactions().stream()
                .filter(t -> t.getVendor().toLowerCase().contains(vendor))
                .toList();
        displayTransactions(results);
    }

    private void displayDeposits() {
        List<Transaction> deposits = repository.getAllTransactions().stream()
                .filter(t -> t.getAmount() > 0)
                .toList();
        displayTransactions(deposits);
    }

    private void displayPayments() {
        List<Transaction> payments = repository.getAllTransactions().stream()
                .filter(t -> t.getAmount() < 0)
                .toList();
        displayTransactions(payments);
    }

    private void displayTransactions(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        transactions.stream()
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .forEach(System.out::println);
    }
}
package com.pluralsight.service;

import com.pluralsight.model.Transaction;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionFileRepository {

    private static final String FILE_NAME = "transactions.csv";

    public TransactionFileRepository() {
        // Create the file if it doesn't exist yet
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating transactions file: " + e.getMessage());
        }
    }

    public void addTransaction(Transaction transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(transaction.getDate() + "|" +
                    transaction.getTime() + "|" +
                    transaction.getDescription() + "|" +
                    transaction.getVendor() + "|" +
                    transaction.getAmount());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] parts = line.split("\\|");
                if (parts.length != 5) continue;

                LocalDate date = LocalDate.parse(parts[0].trim());
                LocalTime time = LocalTime.parse(parts[1].trim());
                String description = parts[2].trim();
                String vendor = parts[3].trim();
                double amount = Double.parseDouble(parts[4].trim());

                transactions.add(new Transaction(date, time, description, vendor, amount));
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }

        return transactions;
    }

    public void addTransaction() {
    }
}



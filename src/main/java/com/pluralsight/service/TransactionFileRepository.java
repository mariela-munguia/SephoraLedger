package com.pluralsight.service;

import com.pluralsight.model.Transaction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionFileRepository {
    private final String fileName = "transactions.csv";

    public void addTransaction(Transaction transaction) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(transaction.toCsvString());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String input;

            while ((input = reader.readLine()) != null) {
                String[] parts = input.split("\\|");

                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);

                Transaction transaction = new Transaction(date, time, description, vendor, amount);
                transactions.add(transaction);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        return transactions;
    }
}

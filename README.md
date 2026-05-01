# 💄 Sephora Ledger

> A Java-based accounting ledger application built to track deposits and payments — developed as a backend capstone project.

---

## About the Project

**Sephora Ledger** is a command-line accounting application written in Java that allows users to manage financial transactions. Users can log deposits and payments, view a full ledger history all persisted to a local CSV file.

This project was built to demonstrate core backend development skills including file I/O, object-orientation, and clean application architecture.

---

## Features

- ✅ Add deposits and payments with description, vendor, and amount
- ✅ Persistent storage via CSV file — data survives app restarts
- ✅ Filter by deposits or payments
- ✅ Built-in financial reports:
  - Month to Date
  - Previous Month
  - Year to Date
  - Previous Year

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 26 |
| Build Tool | Maven |
| Storage | CSV flat file |
| Architecture | Layered (UI / Service / Model) |
| IDE | IntelliJ IDEA |

---

## Project Structure

```
SephoraLedger/
├── src/
│   └── main/
│       └── java/
│           └── com/pluralsight/
│               ├── Main.java
│               ├── model/
│               │   └── Transaction.java
│               ├── service/
│               │   └── TransactionFileRepository.java
│               └── ui/
│                   └── LedgerApp.java
├── transactions.csv
└── pom.xml
```

---

### Prerequisites

- Java 17 or higher
- Maven (or run directly in IntelliJ)

---

## How to Use

```
==========*** Sephora Ledger ***==========
======*** Accounting Ledger App ***========
D) Add Deposit
P) Make Payment
L) Ledger
X) Exit
Choose an option:
```

**Adding a Deposit:**
```
Description: Brand Sponsorship Payment
Vendor: Fenty Beauty
Amount: 500.00
Your Deposit Has Been Saved.
```

**Viewing the Ledger:**
```
A) All
D) Deposits
P) Payments
R) Reports
H) Home
```

---

## CSV Format

Transactions are stored in `transactions.csv` with the following format:

```
date|time|description|vendor|amount
2026-05-01|10:30:00|Brand Sponsorship|Fenty Beauty|500.00
2026-05-01|11:00:00|Lipstick Collection|Charlotte Tilbury|-89.00
```

Deposits are stored as **positive** values. Payments are stored as **negative** values.

---

## Acknowledgements

Built as a capstone project for the **Year Up / Pluralsight** backend Java program.  
Inspired by a love of beauty, business, and clean code. 💋

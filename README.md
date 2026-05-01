
# SEPHORA LEDGER

<div align="center">

![Java](https://img.shields.io/badge/Java-26-000000?style=flat-square&labelColor=000000&color=ffffff)
![Maven](https://img.shields.io/badge/Maven-Build-000000?style=flat-square&labelColor=000000&color=ffffff)
![Status](https://img.shields.io/badge/Status-Complete-000000?style=flat-square&labelColor=000000&color=ffffff)
![Internship](https://img.shields.io/badge/Open%20To-Internships-000000?style=flat-square&labelColor=000000&color=e8000d)

**A backend accounting ledger application built in Java.**
*Capstone Project*

</div>

---

## OVERVIEW

Sephora Ledger is a command-line financial tracking application that allows users to log deposits and payments, view transaction history. All data is persisted to a local CSV file, ensuring transactions are retained between sessions.

This project demonstrates core backend engineering competencies including file I/O, object-oriented programming, layered architecture, and data filtering using Java Streams.

---

## FEATURES

| Feature | Description |
|---|---|
| Add Deposit | Log income with description, vendor, and amount |
| Make Payment | Record expenses stored as negative values |
| Ledger View | Display all transactions sorted newest-first |
| Persistence | All data saved to `transactions.csv` |


---

## ARCHITECTURE

```
SephoraLedger/
├── src/main/java/com/pluralsight/
│   ├── Main.java                          # Entry point
│   ├── ui/
│   │   └── LedgerApp.java                # Menu navigation & user input
│   ├── service/
│   │   └── TransactionFileRepository.java # CSV read/write logic
│   └── model/
│       └── Transaction.java              # Transaction data model
├── transactions.csv                       # Persistent data store
└── pom.xml
```

**Design Pattern:** Three-layer architecture separating UI, business logic, and data access.

---

## TECH STACK

| | |
|---|---|
| Language | Java 26 |
| Build Tool | Maven |
| Data Storage | CSV flat file |
| File I/O | BufferedReader / BufferedWriter |
| Date & Time | java.time (LocalDate, LocalTime) |
| IDE | IntelliJ IDEA |

---


## APPLICATION PREVIEW

```
💄 =============================== 💄
        ✨ SEPHORA LEDGER ✨
      💰 Accounting Ledger App 💰
💄 =============================== 💄

  💵  D) Add Deposit
  💸  P) Make Payment
  📒  L) Ledger
  🚪  X) Exit

👉 Choose an option: D

Description: Brand Sponsorship
Vendor: Fenty Beauty
Amount: 500.00
✅ Your Deposit Has Been Saved! 💵
```

---

## DATA FORMAT

Transactions are stored in pipe-delimited format:

```
2026-05-01|10:30:00|Brand Sponsorship|Fenty Beauty|500.00
2026-05-01|11:00:00|Lipstick Collection|Charlotte Tilbury|-89.00
```

> Deposits are positive values. Payments are negative values.

---

## Thought Process 

**Why CSV over a database?**
Flat file storage keeps the application dependency-free and portable. The pipe delimiter avoids conflicts with commas in transaction descriptions.

**Why negative values for payments?**
Storing payments as negative amounts allows a single `getAllTransactions()` method to power all views — deposits, payments, and reports — using a single stream filter on the amount field.

---

## FUTURE IMPROVEMENTS

- [ ] Replace CSV with PostgreSQL database
- [ ] Add user authentication and multi-account support
- [ ] Build a REST API layer using Spring Boot
- [ ] Create a web-based front end

---

</div>Built as a capstone project.  
Inspired by a love of beauty, business, and clean code. 💋

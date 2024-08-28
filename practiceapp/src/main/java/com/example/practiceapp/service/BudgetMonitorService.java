package com.example.practiceapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class BudgetMonitorService {

    private final Map<String, Double> budgetThresholds = new HashMap<>();
    private final Map<String, Double> currentSpendings = new ConcurrentHashMap<>();

    public BudgetMonitorService() {
        // Initialize budget thresholds and current spendings
        budgetThresholds.put("Food", 500.0);
        budgetThresholds.put("Entertainment", 300.0);
        budgetThresholds.put("Bills", 400.0);

        currentSpendings.put("Food", 0.0);
        currentSpendings.put("Entertainment", 0.0);
        currentSpendings.put("Bills", 0.0);
    }

    public void simulateTransactions() {
        // Simulate asynchronous transaction fetching
        CompletableFuture.runAsync(this::processTransactions);
    }

    private void processTransactions() {
        // Simulate transactions
        List<Transaction> transactions = List.of(
            new Transaction("Food", 50.0),
            new Transaction("Entertainment", 100.0),
            new Transaction("Bills", 200.0)
        );

        transactions.parallelStream().forEach(this::updateBudget);
    }

    private void updateBudget(Transaction transaction) {
        String category = transaction.getCategory();
        currentSpendings.computeIfPresent(category, (k, v) -> v + transaction.getAmount());

        if (currentSpendings.get(category) > budgetThresholds.get(category)) {
            System.out.println("Alert: " + category + " budget exceeded!");
        }
    }

    public static class Transaction {
        private final String category;
        private final double amount;

        public Transaction(String category, double amount) {
            this.category = category;
            this.amount = amount;
        }

        public String getCategory() {
            return category;
        }

        public double getAmount() {
            return amount;
        }
    }
    
    @Scheduled(fixedRate = 10000)
    public void scheduledMonitor() {
        simulateTransactions();
    }
}

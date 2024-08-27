package com.example.practiceapp.service;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RecurringExpenseService {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2); // Increased the thread pool size for concurrency
    private final Map<String, RecurringExpense> recurringExpenses = new ConcurrentHashMap<>();
    private final Logger logger = LoggerFactory.getLogger(RecurringExpenseService.class);

    public RecurringExpenseService() {
        // Initialize with some recurring expenses
        recurringExpenses.put("Rent", new RecurringExpense("Rent", 1200.0, LocalDate.now().plusDays(5)));
        recurringExpenses.put("Gym Membership", new RecurringExpense("Gym Membership", 50.0, LocalDate.now().plusDays(1)));
        recurringExpenses.put("Internet", new RecurringExpense("Internet", 60.0, LocalDate.now().plusDays(10)));

        // Schedule the check to run every hour
        scheduler.scheduleAtFixedRate(
            this::checkRecurringExpenses, 
            0, 
            1, 
            TimeUnit.HOURS
        );
    }

    @Scheduled(fixedRate = 5000) // Just for demonstration, this will log every 5 seconds
    public void logSchedulerActivity() {
        logger.info("Scheduled task triggered at {}", LocalDate.now());
    }

    public void checkRecurringExpenses() {
        String threadName = Thread.currentThread().getName();
        logger.info("Thread [{}] - Starting check for recurring expenses...", threadName);
        for (Map.Entry<String, RecurringExpense> entry : recurringExpenses.entrySet()) {
            RecurringExpense expense = entry.getValue();
            logger.info("Thread [{}] - Checking expense: {}", threadName, expense.getName());

            // Check if the expense is due soon (e.g., within 3 days)
            if (expense.getDueDate().isBefore(LocalDate.now().plusDays(3))) {
                logger.warn("Thread [{}] - Recurring expense {} is due soon. Amount: ${}", threadName, expense.getName(), expense.getAmount());
                // Simulate sending a notification
                sendNotification(expense);
            } else {
                logger.info("Thread [{}] - Recurring expense {} is not due yet. Due on: {}", threadName, expense.getName(), expense.getDueDate());
            }
        }
        logger.info("Thread [{}] - Completed check for recurring expenses.", threadName);
    }

    private void sendNotification(RecurringExpense expense) {
        String threadName = Thread.currentThread().getName();
        // Simulate notification logic
        logger.info("Thread [{}] - Notification sent for expense: {}", threadName, expense.getName());
    }

    // Inner class representing a recurring expense
    private static class RecurringExpense {
        private final String name;
        private final double amount;
        private final LocalDate dueDate;

        public RecurringExpense(String name, double amount, LocalDate dueDate) {
            this.name = name;
            this.amount = amount;
            this.dueDate = dueDate;
        }

        public String getName() {
            return name;
        }

        public double getAmount() {
            return amount;
        }

        public LocalDate getDueDate() {
            return dueDate;
        }
    }
}

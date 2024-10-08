package com.example.practiceapp.controller;

import com.example.practiceapp.service.TransactionService;
import com.example.practiceapp.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/export")
public class ExportController {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ExportController.class);

    private final TransactionService transactionService;

    @Autowired
    public ExportController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<String> exportTransactionsToCSV(@RequestParam(required = false, defaultValue = "/app/Temp/transactions.csv") String filePath) {
        List<Transaction> transactions = transactionService.getAllTransactions();

        if (transactions.isEmpty()) {
            logger.warn("No transactions available to export.");
            return new ResponseEntity<>("No transactions available to export", HttpStatus.NO_CONTENT);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("ID,Category,Amount,Date\n");
            for (Transaction transaction : transactions) {
                writer.write(transaction.getId() + "," + transaction.getCategory() + "," + transaction.getAmount() + "," + transaction.getDate() + "\n");
            }

            logger.info("Transactions successfully written to file: {}", filePath);
            return new ResponseEntity<>("Transactions exported successfully", HttpStatus.OK);

        } catch (IOException e) {
            logger.error("Error writing transactions to CSV file: {}", e.getMessage());
            return new ResponseEntity<>("Failed to export transactions", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

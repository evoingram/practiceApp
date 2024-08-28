package com.example.practiceapp.service;

import com.example.practiceapp.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class ExportService {

    private static final Logger logger = LoggerFactory.getLogger(ExportService.class);

    public String exportTransactionsToCSV(List<Transaction> transactions, String filePath) {
        logger.info("Starting export of transactions to CSV.");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Transaction ID,Category,Amount,Date\n");

            for (Transaction transaction : transactions) {
                writer.write(transaction.getId() + "," + transaction.getCategory() + "," +
                        transaction.getAmount() + "," + transaction.getDate() + "\n");

                logger.info("Writing transaction: ID={}, Category={}, Amount={}, Date={}",
                        transaction.getId(), transaction.getCategory(), transaction.getAmount(), transaction.getDate());
            }

            logger.info("Completed export of transactions to CSV. File saved at: {}", filePath);
            return "Data exported successfully to " + filePath;
        } catch (IOException e) {
            logger.error("Error occurred while exporting data: ", e);
            return "Error occurred while exporting data: " + e.getMessage();
        }
    }
}

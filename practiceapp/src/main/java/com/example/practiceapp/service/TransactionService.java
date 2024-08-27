package com.example.practiceapp.service;

import com.example.practiceapp.model.Transaction;
import com.example.practiceapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class TransactionService {
    // Add logger instance
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);


    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        logger.info("Fetched transactions: {}", transactions);
        return transactions;
    }
    
}

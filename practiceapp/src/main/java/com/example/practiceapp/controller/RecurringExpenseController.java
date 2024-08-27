package com.example.practiceapp.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.practiceapp.service.RecurringExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/recurring-expenses")
public class RecurringExpenseController {

    private final RecurringExpenseService recurringExpenseService;

    @Autowired
    public RecurringExpenseController(RecurringExpenseService recurringExpenseService) {
        this.recurringExpenseService = recurringExpenseService;
    }

    @PostMapping("/check")
    public ResponseEntity<String> triggerCheck() {
        recurringExpenseService.checkRecurringExpenses();
        return new ResponseEntity<>("Recurring expense check triggered.", HttpStatus.OK);
    }
}

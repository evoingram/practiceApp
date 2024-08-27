package com.example.practiceapp.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.practiceapp.service.BudgetMonitorService;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    private final BudgetMonitorService budgetMonitorService;

    @Autowired
    public BudgetController(BudgetMonitorService budgetMonitorService) {
        this.budgetMonitorService = budgetMonitorService;
    }

    @PostMapping("/simulate")
    public ResponseEntity<String> simulateTransactions() {
        budgetMonitorService.simulateTransactions();
        return new ResponseEntity<>("Simulation started", HttpStatus.OK);
    }
}

package com.example.Income.controller;

import com.example.Income.dto.IncomeDTO;
import com.example.Income.model.Income;
import com.example.Income.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    //adding income
    @PostMapping("/income")
    public ResponseEntity<String> addIncome(@RequestBody IncomeDTO incomeDTO, @RequestHeader("X-User-Id")Long userId) {
        incomeService.addIncome(incomeDTO, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Income added successfully!!");
    }

    //updating income
//    @PostMapping("/income/update")
//    public ResponseEntity<String> updateIncome(@RequestBody IncomeDTO incomeDTO) {
//        //updating the income
//        incomeService.updateIncome(incomeDTO);
//        return ResponseEntity.ok("Income updated successfully!!");
//    }

    @GetMapping("/income/healthCheck")
    public String healthCheck() {
        return "Income service is up and running!!";
    }
}

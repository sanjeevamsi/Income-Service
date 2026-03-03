package com.example.Income.controller;

import com.example.Income.dto.IncomeDTO;
import com.example.Income.model.Income;
import com.example.Income.service.IncomeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    //adding
    @PostMapping("/income")
    public ResponseEntity<String> addIncome(@Valid @RequestBody IncomeDTO incomeDTO, @RequestHeader("X-User-Id")String userId) {
        try{
            Long userIdLong = Long.parseLong(userId);
            incomeService.addIncome(incomeDTO, userIdLong);
        } catch (NumberFormatException e) {
            System.out.println("Invalid User ID format: " + userId);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid User ID format!!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Income added successfully!!");
    }

    //updating
    @PutMapping("/income/{id}")
    public ResponseEntity<String> updateIncome(@RequestHeader("X-User-Id")Long userId, @Valid @RequestBody IncomeDTO incomeDTO, @PathVariable Long id) {
        incomeService.updateIncome(userId, incomeDTO, id);
        return ResponseEntity.ok("Income updated successfully!!");
    }

    //delete
    @DeleteMapping("/income/{deleteId}")
    public ResponseEntity<String> deleteIncome(@RequestHeader("X-User-Id") Long userId, @PathVariable Long deleteId) {
        incomeService.deleteIncome(userId, deleteId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Income deleted successfully!!");
    }

    //get all incomes for the logged in user
    @GetMapping("/getAllIncomes")
    public ResponseEntity<Page<Income>> getALlIncomes(@RequestHeader("X-User-Id")Long userId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(incomeService.getAllIncomes(userId, page, size));
    }

    @GetMapping("/income/healthCheck")
    public String healthCheck() {
        return "Income service is up and running!!";
    }
}

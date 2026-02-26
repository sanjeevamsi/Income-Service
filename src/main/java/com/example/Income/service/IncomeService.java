package com.example.Income.service;

import com.example.Income.dto.IncomeDTO;
import com.example.Income.model.Income;
import com.example.Income.repo.IncomeCategoryRepo;
import com.example.Income.repo.IncomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class IncomeService {
    @Autowired
    private IncomeRepo incomeRepo;
    @Autowired
    private IncomeCategoryRepo categoryRepo;

    public void addIncome(IncomeDTO dto, Long userIdFromGateway) {
        //fetch the logged in user
        Income income = new Income();

        income.setAmount(dto.getAmount());
        income.setSource(dto.getSource());
        income.setDescription(dto.getDescription());
        income.setCreatedAt(LocalDate.now());
        income.setIncomeCategory(categoryRepo.findById(dto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found!!")));
        //set the user id from the gateway
        income.setUserId(userIdFromGateway);
        incomeRepo.save(income);
    }
}

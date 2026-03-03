package com.example.Income.service;

import com.example.Income.dto.IncomeDTO;
import com.example.Income.model.Income;
import com.example.Income.model.IncomeCategory;
import com.example.Income.repo.IncomeCategoryRepo;
import com.example.Income.repo.IncomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class IncomeService {
    @Autowired
    private IncomeRepo incomeRepo;
    @Autowired
    private IncomeCategoryRepo categoryRepo;

    private PageRequest pageRequest;

    public void addIncome(IncomeDTO dto, Long userIdFromGateway) {
        Income income = new Income();
        income.setUserId(userIdFromGateway);
        income.setCreatedAt(LocalDate.now());
        mapDtoToEntity(dto, income);
        incomeRepo.save(income);
    }

    public void updateIncome(Long userId, IncomeDTO incomeDTO, Long id) {
        Income income = incomeRepo.findByIdAndUserId(id,userId)
                .orElseThrow(() -> new RuntimeException("Not found"));
        income.setUpdatedAt(LocalDate.now());
        mapDtoToEntity(incomeDTO, income);
        incomeRepo.save(income);
    }

    private void mapDtoToEntity(IncomeDTO dto, Income income) {
        income.setAmount(dto.getAmount());
        income.setSource(dto.getSource());
        income.setDescription(dto.getDescription());
        income.setCreatedAt(LocalDate.now());
        IncomeCategory cat = categoryRepo.findById(dto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found!!"));
        income.setIncomeCategory(cat);
    }

    public void deleteIncome(Long userIdFromGateway, Long deleteId) {
        Income income = incomeRepo.findById(deleteId).orElseThrow(() -> new RuntimeException("Income record not found!!"));
        if(!income.getUserId().equals(userIdFromGateway)) {
            throw new RuntimeException("Unauthorized");
        }
        incomeRepo.delete(income);
    }

    public Page<Income> getAllIncomes(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return incomeRepo.findAllByUserId(userId, pageable);
    }
}

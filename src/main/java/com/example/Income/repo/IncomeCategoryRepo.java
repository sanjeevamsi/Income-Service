package com.example.Income.repo;

import com.example.Income.model.IncomeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeCategoryRepo extends JpaRepository<IncomeCategory, Long> {
}

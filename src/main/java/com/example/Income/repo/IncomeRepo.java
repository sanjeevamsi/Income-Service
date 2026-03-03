package com.example.Income.repo;

import com.example.Income.model.Income;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IncomeRepo extends JpaRepository<Income, Long> {

    Page<Income> findAllByUserId(Long userId, Pageable pageable);

    Optional<Income> findByIdAndUserId(Long id, Long userId);
}

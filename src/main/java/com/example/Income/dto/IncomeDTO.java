package com.example.Income.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncomeDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive(message = "Category ID should be greater than 0")
    @NotNull(message = "Category ID cannot be null")
    private Long categoryId;
    @Positive(message = "Income should be greater than 0")
    @NotNull(message = "Income amount cannot be null")
    private BigDecimal amount;
    @NotNull(message = "Source cannot be null")
    private String source;
    private String description;
}

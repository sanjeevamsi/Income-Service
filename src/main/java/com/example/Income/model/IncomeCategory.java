package com.example.Income.model;

import com.example.Income.enums.IncomeCategoryName;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class IncomeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private IncomeCategoryName name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @OneToMany(mappedBy = "incomeCategory")
    private List<Income> incomes;
}
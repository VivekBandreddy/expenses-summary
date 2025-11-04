package com.expenses.summary.controller;

import com.expenses.summary.entity.ExpensesCategory;
import com.expenses.summary.repository.ExpensesCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/categories")
@RestController
public class ExpensesCategoryController {

    @Autowired
    private ExpensesCategoryRepository expensesCategoryRepository;

    @GetMapping
    public ResponseEntity<List<ExpensesCategory>> getExpensesCategories() {

        return ResponseEntity.ok(expensesCategoryRepository.findAll());
    }
}

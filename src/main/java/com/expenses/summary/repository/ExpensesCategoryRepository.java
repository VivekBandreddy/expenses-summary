package com.expenses.summary.repository;

import com.expenses.summary.entity.ExpensesCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesCategoryRepository extends JpaRepository<ExpensesCategory, Integer> {

}

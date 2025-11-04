package com.expenses.summary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "expenses_category")
@Data
public class ExpensesCategory {

    @Id
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_description")
    private String categoryDescription;

}

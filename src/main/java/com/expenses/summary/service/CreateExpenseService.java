package com.expenses.summary.service;

import com.expenses.summary.model.records.ExpenseDetails;

import java.util.List;
import java.util.Map;

public interface CreateExpenseService extends ExpenseSummaryHelper {

    void createExpense(Map<String, String> headerMap, List<ExpenseDetails> expenseDetailsList, List<String> groupIds);
}

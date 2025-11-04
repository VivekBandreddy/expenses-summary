package com.expenses.summary.service;

import com.expenses.summary.model.records.ExpenseTransactionHistory;
import org.apache.coyote.BadRequestException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface RetrieveExpenseService {
    ExpenseTransactionHistory retrieveExpense(Map<String, String> headerMap, List<String> groupIds, LocalDate startDate, LocalDate endDate) throws BadRequestException;
}

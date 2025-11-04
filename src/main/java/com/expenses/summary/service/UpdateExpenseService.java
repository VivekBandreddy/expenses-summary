package com.expenses.summary.service;

import com.expenses.summary.model.records.ExpenseDetails;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.Map;

public interface UpdateExpenseService extends ExpenseSummaryHelper {
    void updateExpense(Map<String, String> headerMap, List<String> groupIds, ExpenseDetails updateExpense) throws BadRequestException;
}

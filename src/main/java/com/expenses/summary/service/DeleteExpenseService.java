package com.expenses.summary.service;

import org.apache.coyote.BadRequestException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface DeleteExpenseService {
    void deleteExpense(Map<String, String> headerMap, List<String> groupIds, BigDecimal id) throws BadRequestException;
}

package com.expenses.summary.service.impl;

import com.expenses.summary.constants.ExpensesConstants;
import com.expenses.summary.entity.DailyExpensesSummary;
import com.expenses.summary.model.records.ExpenseDetails;
import com.expenses.summary.repository.DailyExpensesSummaryRepository;
import com.expenses.summary.service.CreateExpenseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class CreateExpenseServiceImpl implements CreateExpenseService {


    private DailyExpensesSummaryRepository dailyExpensesRepository;

    @Override
    public void createExpense(Map<String, String> headerMap, List<ExpenseDetails> expenseDetailsList, List<String> groupIds) {
        validateGroupId(expenseDetailsList, groupIds);
        String createdBy = headerMap.get(ExpensesConstants.X_USER_INFO);
        List<DailyExpensesSummary> dailyExpensesList = expenseDetailsList.parallelStream().map(e -> transformToDailyExpenses(e, createdBy)).collect(Collectors.toList());
        log.info("dailyExpensesList : {} ", dailyExpensesList);
        dailyExpensesRepository.saveAll(dailyExpensesList);
    }

}

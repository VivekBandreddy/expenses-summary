package com.expenses.summary.service.impl;

import com.expenses.summary.constants.ExpensesConstants;
import com.expenses.summary.model.records.ExpenseDetails;
import com.expenses.summary.repository.DailyExpensesSummaryRepository;
import com.expenses.summary.service.UpdateExpenseService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Component
public class UpdateExpenseServiceImpl implements UpdateExpenseService {

    private DailyExpensesSummaryRepository dailyExpensesSummaryRepository;

    @Override
    public void updateExpense(Map<String, String> headerMap, List<String> groupIds, ExpenseDetails updateExpense) throws BadRequestException {
        validateGroupId(updateExpense, groupIds);
        dailyExpensesSummaryRepository.save(transformToDailyExpenses(updateExpense, headerMap.get(ExpensesConstants.X_USER_INFO)));
    }
}

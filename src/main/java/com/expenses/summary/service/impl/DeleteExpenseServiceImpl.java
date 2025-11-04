package com.expenses.summary.service.impl;

import com.expenses.summary.entity.DailyExpensesSummary;
import com.expenses.summary.repository.DailyExpensesSummaryRepository;
import com.expenses.summary.service.DeleteExpenseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class DeleteExpenseServiceImpl implements DeleteExpenseService {

    private DailyExpensesSummaryRepository dailyExpensesRepository;

    @Override
    public void deleteExpense(Map<String, String> headerMap, List<String> groupIds, BigDecimal id) throws BadRequestException {

        Optional<DailyExpensesSummary> existingExpense = dailyExpensesRepository.findById(id);

        if (existingExpense.isEmpty()) {
            log.error("No transactions found for id : {} ", id);
            //TODO replace with custom exception
            throw new BadRequestException();
        }

        if (!groupIds.contains(existingExpense.get().getGroupId())) {
            log.error("user has not access to delete the transaction");
            //todo replace with custom exception
            throw new BadRequestException();
        }
        dailyExpensesRepository.deleteById(id);

    }
}

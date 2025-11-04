package com.expenses.summary.service.impl;

import com.expenses.summary.constants.ExpensesConstants;
import com.expenses.summary.entity.DailyExpensesSummary;
import com.expenses.summary.model.records.ExpenseDetails;
import com.expenses.summary.model.records.ExpenseTransactionHistory;
import com.expenses.summary.repository.DailyExpensesSummaryRepository;
import com.expenses.summary.service.RetrieveExpenseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class RetrieveExpenseServiceImpl implements RetrieveExpenseService {

    private DailyExpensesSummaryRepository dailyExpensesSummaryRepository;

    @Override
    public ExpenseTransactionHistory retrieveExpense(Map<String, String> headerMap, List<String> groupIds, LocalDate startDate, LocalDate endDate) throws BadRequestException {
        log.info("Headers Map : {} , groupIds : {} , start date : {} , end date : {}  ", headerMap, groupIds, startDate, endDate);

        String customerId = headerMap.get(ExpensesConstants.X_USER_INFO);
        if (startDate == null) startDate = LocalDate.now().minusDays(31);
        if (endDate == null) endDate = LocalDate.now();
        Optional<List<DailyExpensesSummary>> optionalTransactionsList = dailyExpensesSummaryRepository.findByGroupIdInAndTransactionDateBetween(groupIds, startDate, endDate);
        //dailyExpensesSummaryRepository.findByGroupIdIn(groupIds);

        if (optionalTransactionsList.isEmpty())
            return new ExpenseTransactionHistory(ExpensesConstants.LONG_ZER0, ExpensesConstants.ZERO_AMOUNT, null, null);

        log.info("transactions details size from DB : {} ", optionalTransactionsList.get().size());
        AtomicReference<BigDecimal> totalAmount = new AtomicReference<>(ExpensesConstants.ZERO_AMOUNT);
        List<ExpenseDetails> expenseDetailsList = new ArrayList<>();

        Map<Integer, Map<String, BigDecimal>> transactionsMap = optionalTransactionsList.get().stream().filter(x -> x.getMids().contains(customerId)).map(x -> {
            totalAmount.set(totalAmount.get().add(x.getAmount()));
            ExpenseDetails expenseDetails = transFormExpenseDetails(x);
            expenseDetailsList.add(expenseDetails);
            return expenseDetails;
        }).collect(Collectors.groupingBy(ExpenseDetails::categoryCode, Collectors.toMap(ExpenseDetails::description, ExpenseDetails::amount, BigDecimal::add))
                //Collectors.toMap(ExpenseDetails::description, ExpenseDetails::amount, BigDecimal::add)
        );

        Comparator<ExpenseDetails> reversed = Comparator.comparing(ExpenseDetails::transactionDate).thenComparing(ExpenseDetails::amount).thenComparing(ExpenseDetails::description).thenComparing(ExpenseDetails::id).reversed();
        expenseDetailsList.sort(reversed);
        return new ExpenseTransactionHistory(expenseDetailsList.size(), totalAmount.get(), expenseDetailsList, transactionsMap);
    }

    private ExpenseDetails transFormExpenseDetails(DailyExpensesSummary dailyExpensesSummary) {
        return new ExpenseDetails(dailyExpensesSummary.getAmount(), dailyExpensesSummary.getSubCategoryCode(), dailyExpensesSummary.getDescription(), dailyExpensesSummary.getSno(), dailyExpensesSummary.getTransactionDate(), dailyExpensesSummary.getGroupId());
    }
}

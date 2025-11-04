package com.expenses.summary.service;

import com.expenses.summary.entity.DailyExpensesSummary;
import com.expenses.summary.model.records.ExpenseDetails;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface ExpenseSummaryHelper {

    default DailyExpensesSummary transformToDailyExpenses(ExpenseDetails e, String createdBy) {
        DailyExpensesSummary dailyExpensesSummary = new DailyExpensesSummary();
        dailyExpensesSummary.setSno(e.id());
        dailyExpensesSummary.setAmount(e.amount());
        dailyExpensesSummary.setMids(e.memberIds());
        dailyExpensesSummary.setDescription(e.description());
        dailyExpensesSummary.setGroupId(e.groupId());
        dailyExpensesSummary.setCreatedBy(createdBy);
        dailyExpensesSummary.setGroupId(e.groupId());
        dailyExpensesSummary.setSubCategoryCode(e.categoryCode());
        dailyExpensesSummary.setTransactionDate(e.transactionDate());
        return dailyExpensesSummary;
    }

    default void validateGroupId(ExpenseDetails expenseDetails, List<String> groupIdList) throws BadRequestException {
        if (!groupIdList.contains(expenseDetails.groupId())) {
            //Todo replace with actual error
            throw new BadRequestException();
        }

    }

    default void validateGroupId(List<ExpenseDetails> expenseDetailsList, List<String> groupIdList) {

        expenseDetailsList.stream().forEach(x -> {
            try {
                validateGroupId(x, groupIdList);
            } catch (BadRequestException e) {
                //Todo replace with actual error
                throw new RuntimeException(e);
            }
        });
    }
}

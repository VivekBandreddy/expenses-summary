package com.expenses.summary.model.records;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

//@JacksonXmlRootElement(localName = "ExpenseTransactionHistory")
public record ExpenseTransactionHistory(long transactionCount, BigDecimal totalAmount,
                                        List<ExpenseDetails> expenseDetailsList,
                                        Map<Integer, Map<String, BigDecimal>> transactionGroupSummary) {
}

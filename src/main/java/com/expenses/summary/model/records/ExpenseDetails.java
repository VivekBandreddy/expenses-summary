package com.expenses.summary.model.records;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseDetails(@NotNull @DecimalMin(value = "0.01") BigDecimal amount, @NotNull int categoryCode,
                             @NotEmpty String description, @NotEmpty String groupId, Long id,
                             @NotEmpty String memberIds, @NotNull LocalDate transactionDate, String createdBy) {

    public ExpenseDetails(BigDecimal amount, int categoryCode, String description, Long id, LocalDate transactionDate, String groupId) {
        this(amount, categoryCode, description, groupId, id, null, transactionDate, null);
    }

    /*public static class Builder {
        private BigDecimal amount;
        private int categoryCode;
        private String description;
        private String groupId;
        private String memberIds;
        private LocalDateTime transactionDateTime;
        private String createdBy;

        //public
    }*/
}

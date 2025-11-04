package com.expenses.summary.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "daily_expenses_summary")
@Data
public class DailyExpensesSummary {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
    //@SequenceGenerator(name = "seq_generator", sequenceName = "daily_expenses_summary_seq", allocationSize = 1)
    //@Transient
    //@Column(name = "sno", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;
    @Column(name = "group_id")
    private String groupId;
    private String mids;
    @Column(name = "sub_category_code")
    private int subCategoryCode;
    private String description;
    private BigDecimal amount;
    @Column(name = "transaction_date")
    private LocalDate transactionDate;
    @Column(name = "created_by")
    private String createdBy;
}

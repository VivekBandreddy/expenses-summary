package com.expenses.summary.repository;

import com.expenses.summary.entity.DailyExpensesSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyExpensesSummaryRepository extends JpaRepository<DailyExpensesSummary, BigDecimal> {

    //@Query(nativeQuery = true, value = "select * from daily_expenses_summary where groupId in ? 0")
    //@Query(nativeQuery = true, value = "select * from daily_expenses_summary where groupId in : groupIds")
    Optional<List<DailyExpensesSummary>> findByGroupIdIn(List<String> groupIds);

    Optional<List<DailyExpensesSummary>> findByGroupIdInAndTransactionDateBetween(List<String> groupIds, LocalDate startDate, LocalDate endDate);
}

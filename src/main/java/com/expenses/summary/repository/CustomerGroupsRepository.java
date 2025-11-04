package com.expenses.summary.repository;

import com.expenses.summary.entity.CustomerGroups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerGroupsRepository extends JpaRepository<CustomerGroups, String> {
}

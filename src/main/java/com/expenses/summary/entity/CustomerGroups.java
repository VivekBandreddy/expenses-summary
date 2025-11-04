package com.expenses.summary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Table
@Entity(name = "customer_groups")
@Data
public class CustomerGroups {

    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "group_id")
    private String groupId;
}

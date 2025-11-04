package com.expenses.summary.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties(prefix = "expenses")
@Component
@Data
public class ExpensesConfig {
    List<String> mandatoryHeaders;

}

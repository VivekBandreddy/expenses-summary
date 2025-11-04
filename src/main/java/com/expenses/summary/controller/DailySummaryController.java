package com.expenses.summary.controller;

//import com.expenses.summary.model.ExpenseDetails;

import com.expenses.summary.constants.ExpensesConstants;
import com.expenses.summary.model.records.ExpenseDetails;
import com.expenses.summary.model.records.ExpenseTransactionHistory;
import com.expenses.summary.service.CreateExpenseService;
import com.expenses.summary.service.DeleteExpenseService;
import com.expenses.summary.service.RetrieveExpenseService;
import com.expenses.summary.service.UpdateExpenseService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dailySummary")
@AllArgsConstructor
public class DailySummaryController {

    private CreateExpenseService createExpenseService;
    private RetrieveExpenseService retrieveExpenseService;
    private UpdateExpenseService updateExpenseService;
    private DeleteExpenseService deleteExpenseService;


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ExpenseTransactionHistory> retrieveExpenses(@RequestHeader Map<String, String> headerMap, HttpServletRequest request, @RequestParam(required = false) LocalDate startDate, @RequestParam(required = false) LocalDate endDate) throws BadRequestException {
        List<String> groupIds = (List<String>) request.getAttribute(ExpensesConstants.X_USER_GROUPS);
        return ResponseEntity.ok(retrieveExpenseService.retrieveExpense(headerMap, groupIds, startDate, endDate));
    }

    @PostMapping
    public ResponseEntity<Void> createExpenses(@RequestHeader Map<String, String> headerMap, HttpServletRequest request, @RequestBody List<ExpenseDetails> expenseDetailsList) {
        List<String> groupIds = (List<String>) request.getAttribute(ExpensesConstants.X_USER_GROUPS);
        createExpenseService.createExpense(headerMap, expenseDetailsList, groupIds);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> updateExpenses(@RequestHeader Map<String, String> headerMap, HttpServletRequest request, @RequestBody ExpenseDetails expenseDetails) throws BadRequestException {
        List<String> groupIds = (List<String>) request.getAttribute(ExpensesConstants.X_USER_GROUPS);
        updateExpenseService.updateExpense(headerMap, groupIds, expenseDetails);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpenses(@RequestHeader Map<String, String> headerMap, HttpServletRequest request, @PathVariable BigDecimal id) throws BadRequestException {
        List<String> groupIds = (List<String>) request.getAttribute(ExpensesConstants.X_USER_GROUPS);
        deleteExpenseService.deleteExpense(headerMap, groupIds, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

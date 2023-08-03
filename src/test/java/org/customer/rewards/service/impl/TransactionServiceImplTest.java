package org.customer.rewards.service.impl;

import org.customer.rewards.model.Transaction;
import org.customer.rewards.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest

@Sql(value = "classpath:test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class TransactionServiceImplTest {

    @Autowired
    TransactionService transactionService;

    @Test
    public void testAllTransactions_success() {
        List<Transaction> transactionList = transactionService.getTransactions();
        assertEquals(15, transactionList.size());
    }

    @Test
    @Sql(value = "classpath:test-create-one-transaction-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testAllTransactions_createNewRecord() {
        List<Transaction> transactionList = transactionService.getTransactions();
        assertEquals(16, transactionList.size());
    }

    @Test
    @Sql(value = "classpath:cleanup-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testAllTransactions_noRecords() {
        List<Transaction> transactionList = transactionService.getTransactions();
        assertEquals(0, transactionList.size());
    }

    @Test
    public void testTransactionsByCustomer_success() {
        List<Transaction> transactionList = transactionService.getTransactionsByCustomerId(1L);
        assertEquals(5, transactionList.size());
    }

    @Test
    public void testTransactionsByCustomer_customerDoesntExist() {
        List<Transaction> transactionList = transactionService.getTransactionsByCustomerId(-1L);
        assertEquals(0, transactionList.size());
    }

    @Test
    @Sql(value = "classpath:test-create-one-transaction-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testTransactionsByCustomer_createOneRecord() {
        List<Transaction> transactionList = transactionService.getTransactionsByCustomerId(1L);
        assertEquals(6, transactionList.size());
    }
}



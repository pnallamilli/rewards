package org.customer.rewards.service;

import org.customer.rewards.model.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getTransactions();

    List<Transaction> getTransactionsByCustomerId(Long customerId);
}

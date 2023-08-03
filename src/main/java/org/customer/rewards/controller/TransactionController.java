package org.customer.rewards.controller;

import org.customer.rewards.exception.TransactionNotFoundException;
import org.customer.rewards.model.Transaction;
import org.customer.rewards.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping
    public ResponseEntity<Object> getTransactions() {
		logger.info("In TransactionController::getTransactions method start");
		List<Transaction> transactionList = transactionService.getTransactions();
		
		if (transactionList == null || transactionList.size() == 0) {
			logger.error("In RewardsController::getCustomerRewards Customer List is empty");
			throw new TransactionNotFoundException("No transactions found");
		}
		
		return ResponseEntity.ok(transactionList);
	}

	@GetMapping(value = "/customer/{customerId}")
	public ResponseEntity<Object> getTransactionsForUser(@PathVariable Long customerId) {
		logger.info("In TransactionController::getTransactions method start");
		List<Transaction> transactionList = transactionService.getTransactionsByCustomerId(customerId);

		if (transactionList == null || transactionList.size() == 0) {
			logger.error("In RewardsController::getCustomerRewards Customer List is empty");
			throw new TransactionNotFoundException("Transactions not found for customer : " + customerId);
		}

		return ResponseEntity.ok(transactionList);
	}
	
}

package org.customer.rewards.service.impl;

import org.customer.rewards.model.Customer;
import org.customer.rewards.model.Transaction;
import org.customer.rewards.repository.CustomerRepository;
import org.customer.rewards.repository.TransactionRepository;
import org.customer.rewards.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
	private static Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Transaction> getTransactions() {
		logger.info("In TransactionServiceImpl::getTransactions");

		List<Transaction> transactionList = transactionRepository.findAll();
		for (Transaction transaction: transactionList) {
			transaction.setCustomerId(transaction.getCustomer().getId());
		}
		return transactionList;
	}

	@Override
	public List<Transaction> getTransactionsByCustomerId(Long customerId) {
		logger.info("In TransactionServiceImpl::getTransactionsByCustomerId customerId : " + customerId);

		Customer customer = customerRepository.findById(customerId).orElse(null);
		if(customer == null) {
			return Collections.emptyList();
		}

		List<Transaction> transactionList = customer.getTransactions();
		for(Transaction transaction : transactionList) {
			transaction.setCustomerId(customerId);
		}

		return transactionList;
	}
}

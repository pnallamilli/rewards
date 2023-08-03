package org.customer.rewards.service;

import org.customer.rewards.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> calculateRewards();

    Customer calculateRewardsByCustomerId(Long customerId);
}

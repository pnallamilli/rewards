package org.customer.rewards.controller;

import org.customer.rewards.exception.CustomerNotFoundException;
import org.customer.rewards.model.Customer;
import org.customer.rewards.service.CustomerService;
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
@RequestMapping("/rewards")
public class RewardsController {

    private static Logger logger = LoggerFactory.getLogger(RewardsController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<Object> getCustomerRewards() {
        logger.info("In RewardsController::getCustomerRewards method start");
        List<Customer> customerList = customerService.calculateRewards();

        if (customerList == null || customerList.size() == 0) {
            logger.error("In RewardsController::getCustomerRewards Customers not found");
            throw new CustomerNotFoundException("Customers not available");
        }

        return ResponseEntity.ok(customerList);
    }

    @GetMapping(value = "/{customerId}")
    public ResponseEntity<Customer> getCustomerRewardByCustomerId(@PathVariable Long customerId) {
        logger.info("In RewardsController::getCustomerRewardByCustomerId method start");
        Customer customerRewardsSummary = customerService.calculateRewardsByCustomerId(customerId);

        if (customerRewardsSummary == null) {
            logger.error("In RewardsController::getCustomerRewardByCustomerId customer records not found");
            throw new CustomerNotFoundException("Customer with Id::::" + customerId + " does not exist");
        }

        return ResponseEntity.ok(customerRewardsSummary);
    }

}

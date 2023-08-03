package org.customer.rewards.service.impl;

import org.customer.rewards.model.Customer;
import org.customer.rewards.model.Transaction;
import org.customer.rewards.repository.CustomerRepository;
import org.customer.rewards.service.CustomerService;
import org.customer.rewards.util.RewardsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRewardRepo;

    private RewardsUtil rewardsUtil;

    @Override
    public List<Customer> calculateRewards() {
        logger.info("In CustomerServiceImpl::calculateRewards for all start");

        List<Customer> customerList = customerRewardRepo.findAll();
        if (customerList != null && customerList.size() > 0) {
            for (Customer customer : customerList) {
                List<Transaction> setOfTransaction = customer.getTransactions();
                calculateRewardsPerMonth(setOfTransaction, customer);
            }
        }

        logger.info("In CustomerServiceImpl::calculateRewards for all end");
        return customerList;
    }

    @Override
    public Customer calculateRewardsByCustomerId(Long customerId) {
        logger.info("In CustomerServiceImpl::calculateRewardsByCustomerId customerId : " + customerId);

        Customer customerReward = customerRewardRepo.findById(customerId).orElse(null);
        if (customerReward != null) {
            List<Transaction> setOfTransaction = customerReward.getTransactions();
            calculateRewardsPerMonth(setOfTransaction, customerReward);
        }

        logger.info("In CustomerServiceImpl::calculateRewardsByCustomerId customerId : " + customerId + " ends");
        return customerReward;
    }

    private void calculateRewardsPerMonth(List<Transaction> transactionList, Customer customer) {

        Map<String, Long> rewardsPerMonthMap = new HashMap<>(); // key = year-month, value = reward points
        Long totalPoints = 0L;

        for (Transaction transaction : transactionList) {
            Date transactionDate = transaction.getCreationDate();

            String transactionMonth = String.valueOf(transactionDate.getMonth() + 1);
            String transactionYear = String.valueOf(transactionDate.getYear() + 1900);

            if (transactionMonth.length() == 1) transactionMonth = "0" + transactionMonth;

            String transactionYearMonth = String.format("%s-%s", transactionYear, transactionMonth);

            Long rewardPointsPerMonth = rewardsPerMonthMap.getOrDefault(transactionYearMonth, 0L);
            Long transactionRewardPoints = rewardsUtil.calculateRewardAmountPerTrans(transaction.getTransactionAmount());
            totalPoints += transactionRewardPoints;
            rewardsPerMonthMap.put(transactionYearMonth, rewardPointsPerMonth + transactionRewardPoints);
        }

        customer.setRewardPointsPerMonth(rewardsPerMonthMap);
        customer.setLastThreeMonthsRewardPoints(getLastThreeMonthsRewardPoints(rewardsPerMonthMap));
        customer.setTotalRewardPoints(totalPoints);
    }

    private static List<Long> getLastThreeMonthsRewardPoints(Map<String, Long> rewardsPerMonthMap) {

        List<String> rewardPointsEarnedPerMonthList = new ArrayList<>(rewardsPerMonthMap.keySet());
        rewardPointsEarnedPerMonthList.sort((e1, e2) -> e2.compareToIgnoreCase(e1));

        List<Long> lastThreeMonthsRewardPoints = new ArrayList<>();
        for (int i = 0; i < 3 && i < rewardPointsEarnedPerMonthList.size(); i++) {
            lastThreeMonthsRewardPoints.add(rewardsPerMonthMap.get(rewardPointsEarnedPerMonthList.get(i)));
        }
        return lastThreeMonthsRewardPoints;
    }

}

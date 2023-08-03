package org.customer.rewards.service.impl;

import org.customer.rewards.model.Customer;
import org.customer.rewards.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Sql(value = "classpath:test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void testRewardPointsForAllCustomers_success() {
        List<Customer> customerList = customerService.calculateRewards();
        assertEquals(8, customerList.size());
    }


    @Test
    @Sql(value = "classpath:cleanup-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testRewardPointsForAllCustomers_noData() {
        List<Customer> customerList = customerService.calculateRewards();
        assertEquals(0, customerList.size());
    }

    @Test
    public void testRewardPointsByCustomerId_success() {
        Customer customer = customerService.calculateRewardsByCustomerId(1L);
        assertEquals(customer.getId(), 1L);
        assertEquals(customer.getTotalRewardPoints(), 300L);
        assertEquals(customer.getTransactions().size(), 5);
    }

    @Test
    public void testRewardPointsByCustomerId_customerDoesntExist() {
        Customer customer = customerService.calculateRewardsByCustomerId(0L);
        assertNull(customer);
    }

    @Test
    public void testRewardPointsByCustomerId_top3_success() {
        Customer customer = customerService.calculateRewardsByCustomerId(1L);
        assertEquals(customer.getId(), 1L);
        assertEquals(customer.getTotalRewardPoints(), 300L);
        assertEquals(customer.getTransactions().size(), 5);

        List<Long> actual = customer.getLastThreeMonthsRewardPoints();
        List<Long> expected = Arrays.asList(300L, 0L, 0L);
        for (int i = 0; i < 3; i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testRewardPointsByCustomerId_top3_case2_success() {
        Customer customer = customerService.calculateRewardsByCustomerId(2L);
        assertEquals(customer.getId(), 2L);
        assertEquals(customer.getTotalRewardPoints(), 470L);
        assertEquals(customer.getTransactions().size(), 5);

        List<Long> actual = customer.getLastThreeMonthsRewardPoints();
        List<Long> expected = Arrays.asList(180L, 130L, 70L);
        for (int i = 0; i < 3; i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testRewardPointsByCustomerId_top3_noRecords() {
        Customer customer = customerService.calculateRewardsByCustomerId(8L);
        assertEquals(customer.getId(), 8L);
        assertEquals(customer.getTotalRewardPoints(), 0);
        assertEquals(customer.getTransactions().size(), 0);
    }

}

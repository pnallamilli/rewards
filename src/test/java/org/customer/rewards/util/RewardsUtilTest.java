package org.customer.rewards.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RewardsUtilTest {

    @Autowired
    RewardsUtil rewardsUtil;

    @Test
    void testCalculateRewardAmountPerTrans_AmtLessThan1() {
        assertEquals(0L, RewardsUtil.calculateRewardAmountPerTrans(-1L));
        assertEquals(0L, RewardsUtil.calculateRewardAmountPerTrans(0L));
    }

    @Test
    void testCalculateRewardAmountPerTrans_AmtLessThan50() {
        assertEquals(0L, RewardsUtil.calculateRewardAmountPerTrans(10L));
        assertEquals(0L, RewardsUtil.calculateRewardAmountPerTrans(49L));
    }

    @Test
    void testCalculateRewardAmountPerTrans_AmtLessThan100() {
        assertEquals(1L, RewardsUtil.calculateRewardAmountPerTrans(51L));
        assertEquals(10L, RewardsUtil.calculateRewardAmountPerTrans(60L));
        assertEquals(49L, RewardsUtil.calculateRewardAmountPerTrans(99L));
    }

    @Test
    void testCalculateRewardAmountPerTrans_AmtGreaterThan100() {
        assertEquals(50L, RewardsUtil.calculateRewardAmountPerTrans(100L));
        assertEquals(52L, RewardsUtil.calculateRewardAmountPerTrans(101L));
        assertEquals(1850L, RewardsUtil.calculateRewardAmountPerTrans(1000L));
    }
}

package org.customer.rewards.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RewardsUtil {
    private static Logger logger = LoggerFactory.getLogger(RewardsUtil.class);

    public static Long calculateRewardAmountPerTrans(Long transactionAmount) {
        logger.debug("In RewardsUtil::calculateRewardAmountPerTrans Amount : " + transactionAmount);

        Long rewardPoints = 0L;
        if (transactionAmount > 100L) {
            rewardPoints = 2 * (transactionAmount - 100) + 50;
        } else if (transactionAmount > 50) {
            rewardPoints = transactionAmount - 50;
        }

        logger.debug("In RewardsUtil::calculateRewardAmountPerTrans Reward Points :  " + rewardPoints);
        return rewardPoints;
    }
}

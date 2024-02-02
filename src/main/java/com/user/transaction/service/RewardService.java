package com.user.transaction.service;

import java.util.Date;
import java.util.List;

import com.user.transaction.dto.MonthlyRewardPoints;
import com.user.transaction.entity.UserTransactionEntity;

public interface RewardService {
	MonthlyRewardPoints saveRewardPoints(UserTransactionEntity transaction);

	List<UserTransactionEntity> getTransactionsByCustomerAndDate(Long customerId, Date startDate, Date endDate);

	MonthlyRewardPoints getMonthlyRewardPoints(Long customerId, String requestedMonth);

}

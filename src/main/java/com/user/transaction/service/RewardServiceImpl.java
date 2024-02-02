package com.user.transaction.service;

import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.transaction.dto.MonthlyRewardPoints;
import com.user.transaction.entity.UserTransactionEntity;
import com.user.transaction.repository.TransactionRepository;

@Service
public class RewardServiceImpl implements RewardService {
	
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public MonthlyRewardPoints saveRewardPoints(UserTransactionEntity transaction) {
		transaction.setId(UUID.randomUUID().toString());
		double earnedPoints = calculateRewardPoints(transaction.getTransactionAmount());
		transaction.setRewardPoints(earnedPoints);
		transaction.setDate(new Date());
		transactionRepository.save(transaction);
		MonthlyRewardPoints rewards = new MonthlyRewardPoints();
		rewards.setDate(transaction.getDate());
		rewards.setTotalRewardPoints(earnedPoints);
		return rewards;
	}

	private double calculateRewardPoints(double amount) {
		double amountOver100 = 0;
		if(amount>100) {
			amountOver100 = (amount - 100);
		}
		double amount50to100 = amount-amountOver100-50;
		return amountOver100*2 + amount50to100*1;
	}
	
	@Override
    public List<UserTransactionEntity> getTransactionsByCustomerAndDate(Long customerId, Date startDate, Date endDate) {
        return transactionRepository.findByCustomerIdAndDateBetween(customerId, startDate, endDate);
    }
	
	/**
	 *
	 */
	@Override
    public MonthlyRewardPoints getMonthlyRewardPoints(Long customerId, String yearMonth) {
		YearMonth requestedMonth = YearMonth.parse(yearMonth);
        Date startDate = Date.from(requestedMonth.atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(requestedMonth.atEndOfMonth().atTime(23, 59, 59, 999999999).atZone(ZoneId.systemDefault()).toInstant());

        List<UserTransactionEntity> transactions = getTransactionsByCustomerAndDate(customerId, startDate, endDate);
        MonthlyRewardPoints monthlyRewardRecord = new MonthlyRewardPoints();
        double totalRewardPointsSum = 0;
        String monthString = null;
        for (UserTransactionEntity transaction : transactions) {
            monthString = requestedMonth.getMonth().toString();
            totalRewardPointsSum += transaction.getRewardPoints();
        }
        monthlyRewardRecord.setTotalRewardPoints(totalRewardPointsSum);
        monthlyRewardRecord.setTransactions(transactions);
        monthlyRewardRecord.setMonth(monthString);
        
        return monthlyRewardRecord;
    }
}

package com.user.transaction.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.user.transaction.entity.UserTransactionEntity;

public class MonthlyRewardPoints {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date Date;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String month;

	private double totalRewardPoints;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<UserTransactionEntity> transactions;


	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		Date = date;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public List<UserTransactionEntity> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<UserTransactionEntity> transactions) {
		this.transactions = transactions;
	}

	public double getTotalRewardPoints() {
		return totalRewardPoints;
	}

	public void setTotalRewardPoints(double totalRewardPoints) {
		this.totalRewardPoints = totalRewardPoints;
	}

}
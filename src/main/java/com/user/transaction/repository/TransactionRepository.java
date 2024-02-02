package com.user.transaction.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.transaction.entity.UserTransactionEntity;


@Repository
public interface TransactionRepository extends JpaRepository<UserTransactionEntity, String> {

	List<UserTransactionEntity> findByCustomerIdAndDateBetween(Long customerId, Date startDate, Date endDate);

}

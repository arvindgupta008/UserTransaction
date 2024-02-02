package com.user.transaction.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.transaction.dto.MonthlyRewardPoints;
import com.user.transaction.entity.UserTransactionEntity;
import com.user.transaction.service.RewardService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rewards")
public class RewardController {
	
	@Autowired
	private  RewardService rewardService;

	@PostMapping("/recordTransaction")
	public ResponseEntity<MonthlyRewardPoints> recordTransaction(@Valid @RequestBody UserTransactionEntity transaction) {

		MonthlyRewardPoints transactionId = rewardService.saveRewardPoints(transaction);
		return new ResponseEntity<>(transactionId, HttpStatus.CREATED);

	}
	
	@GetMapping("/customer/{customerId}")
    public  ResponseEntity<List<UserTransactionEntity>> getTransactionsByCustomerAndDate(
            @PathVariable Long customerId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return new ResponseEntity<>(rewardService.getTransactionsByCustomerAndDate(customerId, startDate, endDate), HttpStatus.OK);
    }
	
	@GetMapping("/monthlyRewardPoints/{customerId}")
    public ResponseEntity<MonthlyRewardPoints> getMonthlyRewardPoints(@PathVariable Long customerId,@RequestParam String requestedMonth) {
		 MonthlyRewardPoints monthlyRewardList =  rewardService.getMonthlyRewardPoints(customerId, requestedMonth);
        return new ResponseEntity<>(monthlyRewardList, HttpStatus.OK);
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}

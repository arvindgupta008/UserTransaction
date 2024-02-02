package com.user.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UserTransactionManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserTransactionManagementApplication.class, args);
	}
}

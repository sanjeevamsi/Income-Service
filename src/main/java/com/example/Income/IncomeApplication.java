package com.example.Income;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IncomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(IncomeApplication.class, args);
	}

}

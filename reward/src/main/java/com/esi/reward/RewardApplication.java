package com.esi.reward;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RewardApplication {

	public static void main(String[] args) {
		DatabaseInitializer.initialize("reward_db");
		SpringApplication.run(RewardApplication.class, args);
	}

}

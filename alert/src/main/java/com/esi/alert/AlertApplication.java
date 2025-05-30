package com.esi.alert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlertApplication {

	public static void main(String[] args) {
		DatabaseInitializer.initialize("alert_db");
		SpringApplication.run(AlertApplication.class, args);
	}

}

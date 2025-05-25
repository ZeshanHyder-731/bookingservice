package com.esi.bookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookingserviceApplication {

	public static void main(String[] args) {
		DatabaseInitializer.initialize("bookingservice_db");
		SpringApplication.run(BookingserviceApplication.class, args);
	}

}

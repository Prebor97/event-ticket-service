package com.ticket.app.event_ticket_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class EventTicketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventTicketServiceApplication.class, args);
	}

}

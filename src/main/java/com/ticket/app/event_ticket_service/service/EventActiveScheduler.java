package com.ticket.app.event_ticket_service.service;

import com.ticket.app.event_ticket_service.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EventActiveScheduler {
    private static final Logger log = LoggerFactory.getLogger(EventActiveScheduler.class);

    private final EventRepository repository;

    public EventActiveScheduler(EventRepository repository) {
        this.repository = repository;
    }
    
    @Async
    @Scheduled(cron = "0 0 1 * * ?")
    public void markInActiveEvents() {
        log.info("Scanning for in active events.............................................................................................");
        repository.findAll().forEach(event -> {
            if (event.getEventDate().isAfter(LocalDate.now())) {
                event.setStatus(false);
                repository.save(event);
            }
        });

        log.info("Completed the event scanning");
    }
}

package com.ticket.app.event_ticket_service.service;

import com.ticket.app.event_ticket_service.repository.TicketRepository;
import com.ticket.app.eventdto.UserEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);

    private final TicketRepository repository;

    public ConsumerService(TicketRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "event-user-topics", groupId = "notification-group-user")
    public void handleCascadingUserDeletion(UserEvents events){
        log.info("Deleting associated user tickets information.......................................................");
        repository.deleteTicketByUserId(events.getUserId());
        log.info("Successfully deleted user associated ticket");
    }
}

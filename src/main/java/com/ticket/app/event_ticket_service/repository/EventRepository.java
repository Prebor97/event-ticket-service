package com.ticket.app.event_ticket_service.repository;

import com.ticket.app.event_ticket_service.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {

}

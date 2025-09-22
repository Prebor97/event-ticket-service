package com.ticket.app.event_ticket_service.repository;

import com.ticket.app.event_ticket_service.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, String> {

    Page<Event> findByNameContainingIgnoreCase(String name, Pageable pageable);

}

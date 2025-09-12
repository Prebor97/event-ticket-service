package com.ticket.app.event_ticket_service.repository;

import com.ticket.app.event_ticket_service.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketRepository extends JpaRepository<Ticket, String> {
    @Query("delete FROM user_tickets u WHERE u.user_id = userId")
    void deleteTicketByUserId(@Param("userId") String userId);
}

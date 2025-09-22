package com.ticket.app.event_ticket_service.repository;

import com.ticket.app.event_ticket_service.model.Event;
import com.ticket.app.event_ticket_service.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, String> {
    @Modifying
    @Query("delete FROM Ticket u WHERE u.userId = :userId")
    void deleteTicketByUserId(@Param("userId") String userId);

    List<Ticket> findByEvent_EventId(String eventId);


    @Query("""
        SELECT t FROM Ticket t
        WHERE t.email = :email
        AND t.event.eventDate > :thresholdDate
        """)
    List<Ticket> findByEmailAndUpcomingEvents(
            @Param("email") String email,
            @Param("thresholdDate") LocalDate thresholdDate
    );

    @Query("""
        SELECT t FROM Ticket t
        WHERE t.email = :email
        AND t.status = :status
        AND t.event.eventDate > :thresholdDate
        """)
    List<Ticket> findByEmailAndStatusAndUpcomingEvents(
            @Param("email") String email,
            @Param("status") String status,
            @Param("thresholdDate") LocalDate thresholdDate
    );
}

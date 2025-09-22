package com.ticket.app.event_ticket_service.service;

import com.ticket.app.event_ticket_service.dto.response.SingleTicketResponse;
import com.ticket.app.event_ticket_service.dto.response.TicketResponse;
import com.ticket.app.event_ticket_service.dto.response.User;
import com.ticket.app.event_ticket_service.exception.TicketNotFoundException;
import com.ticket.app.event_ticket_service.model.Event;
import com.ticket.app.event_ticket_service.model.Ticket;
import com.ticket.app.event_ticket_service.repository.TicketRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public SingleTicketResponse getTicketById(String ticketId, Authentication authentication){

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(()->
                new TicketNotFoundException("Ticket Not Found"));

        String userId = ticket.getUserId();
        String email = ticket.getEmail();
        String name = ticket.getUserName();

        @SuppressWarnings("unchecked")
        Map<String, Object> details = (Map<String, Object>) authentication.getDetails();
        String loggedInUserId = (String) details.get("userId");
        String role = (String) details.get("role");

         if(!loggedInUserId.equals(userId) && !role.equals("ROLE_ADMIN")){
             throw new AccessDeniedException("You are not authorized to view this ticket");
         }


        Event event = ticket.getEvent();
        com.ticket.app.event_ticket_service.dto.response.Event eventDto = new com.ticket.app.event_ticket_service.dto.response.Event();

        eventDto.setName(event.getName());
        eventDto.setId(event.getEventId());
        eventDto.setDate(event.getEventDate());
        eventDto.setLocation(event.getLocation());

        User user = new User(userId,name,email);

        SingleTicketResponse response = new SingleTicketResponse();
        response.setEvent(eventDto);
        response.setUser(user);
        response.setTicketsBooked(ticket.getQuantity());

        return response;

    }

    public List<TicketResponse> getUserTickets(Authentication authentication, String status){
        LocalDate thresholdDate = LocalDate.now().minusDays(1);

        @SuppressWarnings("unchecked")
        Map<String, Object> details = (Map<String, Object>) authentication.getDetails();
        String email = (String) details.get("email");
        List<Ticket> tickets;

        if (status != null && !status.isBlank()) {
            tickets = ticketRepository.findByEmailAndStatusAndUpcomingEvents(
                    email,
                    status.toUpperCase(),
                    thresholdDate
            );
        } else {
            tickets = ticketRepository.findByEmailAndUpcomingEvents(email, thresholdDate);
        }
        return tickets.stream().map(this::mapToResponse).toList();

    }
     private TicketResponse mapToResponse(Ticket ticket){
        TicketResponse response = new TicketResponse();
        response.setId(ticket.getTicketId());
        response.setName(ticket.getEvent().getName());
        response.setTicketsBooked(ticket.getQuantity());

        return response;
     }



}

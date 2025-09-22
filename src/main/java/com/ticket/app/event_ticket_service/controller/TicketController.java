package com.ticket.app.event_ticket_service.controller;

import com.ticket.app.event_ticket_service.dto.response.SingleTicketResponse;
import com.ticket.app.event_ticket_service.dto.response.TicketResponse;
import com.ticket.app.event_ticket_service.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
     @GetMapping("/{ticketId}")
    public ResponseEntity<SingleTicketResponse> getTicketById(@PathVariable String ticketId, Authentication auth){
            return ResponseEntity.ok(ticketService.getTicketById(ticketId,auth));

    }

    @GetMapping("/my/tickets")
    public ResponseEntity<List<TicketResponse>> getUserTickets(
            @RequestParam(required = false) String status,
            Authentication authentication
    ) {
        List<TicketResponse> tickets = ticketService.getUserTickets(authentication, status);
        return ResponseEntity.ok(tickets);
    }

}

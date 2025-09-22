package com.ticket.app.event_ticket_service.exception;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message){
        super(message);
    }
}

package com.ticket.app.event_ticket_service.dto.response;

public class TicketResponse {
    private String id;
    private String name;
    private Integer ticketsBooked;

    public TicketResponse(String id, String name, Integer ticketsBooked) {
        this.id = id;
        this.name = name;
        this.ticketsBooked = ticketsBooked;
    }

    public TicketResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTicketsBooked() {
        return ticketsBooked;
    }

    public void setTicketsBooked(Integer ticketsBooked) {
        this.ticketsBooked = ticketsBooked;
    }
}

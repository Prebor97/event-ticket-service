package com.ticket.app.event_ticket_service.dto.response;

public class SingleTicketResponse {
    private Event event;
    private User user;
    private Integer ticketsBooked;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getTicketsBooked() {
        return ticketsBooked;
    }

    public void setTicketsBooked(Integer ticketsBooked) {
        this.ticketsBooked = ticketsBooked;
    }

    public SingleTicketResponse(Event event, User user, Integer ticketsBooked) {
        this.event = event;
        this.user = user;
        this.ticketsBooked = ticketsBooked;
    }

    public SingleTicketResponse() {
    }
}

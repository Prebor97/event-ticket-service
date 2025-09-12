package com.ticket.app.event_ticket_service.dto.response;

import java.time.LocalDate;

public class EventResponse {
    private String id;
    private String name;
    private LocalDate date;
    private String location;
    private String description;
    private Integer capacity;
    private Integer availableTickets;

    public EventResponse() {
    }

    public EventResponse(String id, String name, LocalDate date, String location, String description, Integer capacity, Integer availableTickets) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.description = description;
        this.capacity = capacity;
        this.availableTickets = availableTickets;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(Integer availableTickets) {
        this.availableTickets = availableTickets;
    }
}

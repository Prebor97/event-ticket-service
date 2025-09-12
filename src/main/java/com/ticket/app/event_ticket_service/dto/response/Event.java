package com.ticket.app.event_ticket_service.dto.response;

import java.time.LocalDate;

public class Event {
    private String id;
    private String name;
    private LocalDate date;
    private String location;

    public Event(String id, String name, LocalDate date, String location) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
    }

    public Event() {
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
}

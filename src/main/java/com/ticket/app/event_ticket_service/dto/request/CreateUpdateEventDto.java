package com.ticket.app.event_ticket_service.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class CreateUpdateEventDto {
    @NotBlank(message = "Name cannot be null")
    private String name;
    @NotBlank(message = "description cannot be null")
    private String description;
    @NotBlank(message = "Date cannot be null")
    private LocalDate eventDate;
    @NotBlank(message = "Location cannot be null")
    private String location;
    @NotBlank(message = "Available tickets cannot be null")
    private Integer availableTickets;

    public @NotBlank(message = "Name cannot be null") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name cannot be null") String name) {
        this.name = name;
    }

    public @NotBlank(message = "description cannot be null") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "description cannot be null") String description) {
        this.description = description;
    }

    public @NotBlank(message = "Date cannot be null") LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(@NotBlank(message = "Date cannot be null") LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public @NotBlank(message = "Location cannot be null") String getLocation() {
        return location;
    }

    public void setLocation(@NotBlank(message = "Location cannot be null") String location) {
        this.location = location;
    }

    public @NotBlank(message = "Available tickets cannot be null") Integer getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(@NotBlank(message = "Available tickets cannot be null") Integer availableTickets) {
        this.availableTickets = availableTickets;
    }

    public CreateUpdateEventDto() {
    }

    public CreateUpdateEventDto(String name, String description, LocalDate eventDate, String location, Integer availableTickets) {
        this.name = name;
        this.description = description;
        this.eventDate = eventDate;
        this.location = location;
        this.availableTickets = availableTickets;
    }
}

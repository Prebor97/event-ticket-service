package com.ticket.app.event_ticket_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateUpdateEventDto {
    @NotBlank(message = "Name cannot be null")
    private String name;
    @NotBlank(message = "description cannot be null")
    private String description;
    @NotNull(message = "Date cannot be null")
    private LocalDate eventDate;
    @NotBlank(message = "Location cannot be null")
    private String location;
    @NotNull(message = "Available tickets cannot be null")
    private Integer totalTickets;
    @NotNull(message = "Price cannot be null")
    private BigDecimal price;

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

    public @NotNull(message = "Date cannot be null") LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(@NotNull(message = "Date cannot be null") LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public @NotBlank(message = "Location cannot be null") String getLocation() {
        return location;
    }

    public void setLocation(@NotBlank(message = "Location cannot be null") String location) {
        this.location = location;
    }

    public @NotNull(message = "Available tickets cannot be null") Integer getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(@NotNull(message = "Available tickets cannot be null") Integer totalTickets) {
        this.totalTickets = totalTickets;
    }

    public @NotNull(message = "Price cannot be null") BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "Price cannot be null") BigDecimal price) {
        this.price = price;
    }

    public CreateUpdateEventDto() {
    }

    public CreateUpdateEventDto(String name, String description, LocalDate eventDate, String location, Integer availableTickets) {
        this.name = name;
        this.description = description;
        this.eventDate = eventDate;
        this.location = location;
        this.totalTickets = availableTickets;
    }
}

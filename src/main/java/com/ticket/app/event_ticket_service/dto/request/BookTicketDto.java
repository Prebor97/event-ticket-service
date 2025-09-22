package com.ticket.app.event_ticket_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookTicketDto {
    @NotNull(message = "Quantity cannot be blank")
    private Integer quantity;

    public BookTicketDto() {
    }

    public BookTicketDto(Integer quantity) {
        this.quantity = quantity;
    }

    public @NotNull(message = "Quantity cannot be blank") Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull(message = "Quantity cannot be blank") Integer quantity) {
        this.quantity = quantity;
    }
}

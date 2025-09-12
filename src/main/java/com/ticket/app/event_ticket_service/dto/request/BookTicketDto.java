package com.ticket.app.event_ticket_service.dto.request;

import jakarta.validation.constraints.NotBlank;

public class BookTicketDto {
    @NotBlank(message = "Quantity cannot be blank")
    private Integer quantity;

    public BookTicketDto() {
    }

    public BookTicketDto(Integer quantity) {
        this.quantity = quantity;
    }

    public @NotBlank(message = "Quantity cannot be blank") Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotBlank(message = "Quantity cannot be blank") Integer quantity) {
        this.quantity = quantity;
    }
}

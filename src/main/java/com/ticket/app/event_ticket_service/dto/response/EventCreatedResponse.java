package com.ticket.app.event_ticket_service.dto.response;

import java.time.LocalDateTime;

public class EventCreatedResponse {

   private String message;
    private  int code;
   private  LocalDateTime timestamp;

    public EventCreatedResponse(String message, int code, LocalDateTime timestamp) {
        this.message = message;
        this.code = code;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

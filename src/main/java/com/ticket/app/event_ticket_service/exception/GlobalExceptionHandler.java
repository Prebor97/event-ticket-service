package com.ticket.app.event_ticket_service.exception;

import com.ticket.app.event_ticket_service.dto.response.SuccessErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<SuccessErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        SuccessErrorResponse errorResponse = new SuccessErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setCode(HttpStatus.FORBIDDEN.value());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<SuccessErrorResponse> handleEventNotFoundException(EventNotFoundException ex) {
        SuccessErrorResponse errorResponse = new SuccessErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setCode(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}

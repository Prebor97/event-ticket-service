package com.ticket.app.event_ticket_service.controller;

import com.ticket.app.event_ticket_service.dto.request.BookTicketDto;
import com.ticket.app.event_ticket_service.dto.request.CreateUpdateEventDto;
import com.ticket.app.event_ticket_service.dto.response.*;
import com.ticket.app.event_ticket_service.service.EventService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping()
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getEvents() {
        return "You are authorized";
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<EventCreatedResponse> createEvent(@Valid @RequestBody CreateUpdateEventDto eventDto) throws AccessDeniedException {
         return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(eventDto));
    }

    @GetMapping("/{eventId}")
    public EntityModel<EventResponse> getEvent(@PathVariable String eventId){
        EventResponse event = eventService.findEventById(eventId);

        return EntityModel.of(
                event,
                linkTo(methodOn(EventController.class).getEvent(eventId)).withSelfRel(),

                linkTo(methodOn(EventController.class)
                        .bookTicket(eventId, null, null))
                        .withRel("bookTicket")
                        .withType("POST")
        );
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/{eventId}/tickets")
    public ResponseEntity<SuccessErrorResponse> bookTicket(@PathVariable String eventId, @RequestBody BookTicketDto dto, Authentication auth){

        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.bookTicket(dto,eventId,auth));
    }

    @GetMapping("/{eventId}/tickets")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<TicketResponse>> getTicketsByEvent(@PathVariable String eventId){

        return ResponseEntity.ok(eventService.getTicketsByEvent(eventId));

    }
    @DeleteMapping("/{eventId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SuccessErrorResponse> deleteEvent(@PathVariable String eventId){
         return ResponseEntity.ok(eventService.deleteById(eventId));

    }

    @PutMapping("/{eventId}")
    public ResponseEntity<SuccessErrorResponse> updateEvent(@PathVariable String eventId, @RequestBody CreateUpdateEventDto updateEventDto){
        return ResponseEntity.ok(eventService.updateEvent(updateEventDto, eventId));
    }


    @GetMapping("/all")
    public PaginatedResponse<EventResponse> getAllEvents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<EventResponse> eventPage =  eventService.getAllEvents(name, sortBy, page, size);
    return  new PaginatedResponse<>(
                eventPage.getContent(),
                eventPage.getNumber(),
                eventPage.getSize(),
            eventPage.getTotalElements(),
            eventPage.getTotalPages(),
            eventPage.isLast()
    );

    }


}

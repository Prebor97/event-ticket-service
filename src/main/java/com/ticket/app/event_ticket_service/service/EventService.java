package com.ticket.app.event_ticket_service.service;


import com.ticket.app.event_ticket_service.dto.request.BookTicketDto;
import com.ticket.app.event_ticket_service.dto.request.CreateUpdateEventDto;
import com.ticket.app.event_ticket_service.dto.response.*;
import com.ticket.app.event_ticket_service.exception.EventNotFoundException;
import com.ticket.app.event_ticket_service.exception.TicketNotFoundException;
import com.ticket.app.event_ticket_service.model.Event;
import com.ticket.app.event_ticket_service.model.Ticket;
import com.ticket.app.event_ticket_service.repository.EventRepository;
import com.ticket.app.event_ticket_service.repository.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;

    public EventService(EventRepository eventRepository, TicketRepository ticketRepository) {
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
    }

    @Transactional
    public EventCreatedResponse createEvent(CreateUpdateEventDto eventDto) {
        Event event = new Event();
        event.setName(eventDto.getName());
        event.setDescription(eventDto.getDescription());
        event.setLocation(eventDto.getLocation());
        event.setEventDate(eventDto.getEventDate());
        event.setAvailableTickets(eventDto.getAvailableTickets());
        event.setPrice(eventDto.getPrice());
        eventRepository.save(event);

        return new EventCreatedResponse("Event Created Successfully", 201, LocalDateTime.now());

    }

    public EventResponse findEventById(String id) {
        Event event = eventRepository.findById(id).orElseThrow(() ->
                new EventNotFoundException("Event ID Invalid: Not found"));

        EventResponse eventResponse = new EventResponse();
        eventResponse.setId(event.getEventId());
        eventResponse.setName(event.getName());
        eventResponse.setDescription(event.getDescription());
        eventResponse.setLocation(event.getLocation());
        eventResponse.setDate(event.getEventDate());
        eventResponse.setAvailableTickets(event.getAvailableTickets());
        eventResponse.setCapacity(event.getTotalTickets());

        return eventResponse;
    }

    @Transactional
    public SuccessErrorResponse bookTicket(BookTicketDto ticketDto, String eventId, Authentication authentication) {
        @SuppressWarnings("unchecked")
        Map<String, Object> details = (Map<String, Object>) authentication.getDetails();
        String userId = (String) details.get("userId");
        String email = (String) details.get("email");
        String firstName = (String) details.get("firstName");


        Event event = eventRepository.findById(eventId).orElseThrow(() ->
                new EventNotFoundException("Event ID Invalid: Not found"));

        BigDecimal totalPrice = event.getPrice()
                .multiply(BigDecimal.valueOf(ticketDto.getQuantity()));


        Ticket ticket = new Ticket();
        ticket.setEvent(event);
        ticket.setQuantity(ticketDto.getQuantity());
        ticket.setStatus("PENDING");
        ticket.setEmail(email);
        ticket.setUserId(userId);
        ticket.setUserName(firstName);
        ticket.setTotalPrice(totalPrice);
        ticket.setPurchaseDate(LocalDate.now());
        ticket.setUpdatedAt(LocalDate.now());

        ticketRepository.save(ticket);

        return new SuccessErrorResponse(201, "Ticket booked successfully");


    }

    public SuccessErrorResponse updateEvent(CreateUpdateEventDto dto, String eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new
                EventNotFoundException("Event Not found"));
        event.setName(dto.getName());
        event.setDescription(dto.getDescription());
        event.setLocation(dto.getLocation());
        event.setAvailableTickets(dto.getAvailableTickets());
        event.setEventDate(dto.getEventDate());
        event.setPrice(dto.getPrice());
        event.setUpdatedAt(LocalDate.now());

        eventRepository.save(event);

        return new SuccessErrorResponse(200, "Event Updated Successfully");
    }

    public SuccessErrorResponse deleteById(String eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() ->
                new EventNotFoundException("Event Not Found"));

        eventRepository.deleteById(eventId);

        return new SuccessErrorResponse(200, "Event Deleted Successfully");
    }

    public List<TicketResponse> getTicketsByEvent(String eventId) {
        List<Ticket> tickets = ticketRepository.findByEvent_EventId(eventId);

        return tickets.stream()
                .map(ticket -> new TicketResponse(
                        ticket.getTicketId(),
                        ticket.getEvent().getName(),
                        ticket.getQuantity()
                ))
                .toList();
    }

    public Page<EventResponse> getAllEvents(String name, String sortBy, int page, int size) {
        Sort sort = Sort.unsorted();

        if (sortBy != null) {
            if (sortBy.equalsIgnoreCase("name")) {
                sort = Sort.by(Sort.Direction.ASC, "name");
            } else if (sortBy.equalsIgnoreCase("date")) {
                sort = Sort.by(Sort.Direction.ASC, "date");
            }
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Event> events;
        if (name != null && !name.isBlank()) {
            events = eventRepository.findByNameContainingIgnoreCase(name, pageable);
        } else {
            events = eventRepository.findAll(pageable);
        }

        return events.map(this::mapToResponse);
    }

    private EventResponse mapToResponse(Event event) {
        EventResponse response = new EventResponse();
        response.setId(event.getEventId());
        response.setName(event.getName());
        response.setDate(event.getEventDate());
        response.setLocation(event.getLocation());
        response.setDescription(event.getDescription());
        response.setCapacity(event.getTotalTickets());
        response.setAvailableTickets(event.getAvailableTickets());
        return response;
    }


}

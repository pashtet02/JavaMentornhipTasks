package com.example.facade.impl;

import com.example.ecxeption.EventNotFoundException;
import com.example.facade.BookingFacade;
import com.example.model.Event;
import com.example.model.Ticket;
import com.example.model.User;
import com.example.service.EventService;
import com.example.service.TicketService;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class BookingFacadeImpl implements BookingFacade {

    private final EventService eventService;
    private final UserService userService;
    private final TicketService ticketService;

    public BookingFacadeImpl(EventService eventService, UserService userService, TicketService ticketService) {
        this.eventService = eventService;
        this.userService = userService;
        this.ticketService = ticketService;
    }


    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public Ticket buyTicket(String username, long eventId) {
        User user = userService.getUserByUsername(username);
        Event event = eventService.getEvent(eventId).orElseThrow(EventNotFoundException::new);

        Ticket ticket = new Ticket();
        ticket.setEvent(event);
        ticket.setUser(user);
        ticket.setCreationDate(new Date(Calendar.getInstance().getTimeInMillis()));

        return ticketService.saveTicket(ticket);
    }

    @Override
    public Ticket getDetailedTicketInfo(long eventId) {
        Ticket ticket = new Ticket();
        Event event = eventService.getEvent(eventId)
                .orElseThrow(EventNotFoundException::new);
        ticket.setEvent(event);
        return ticket;
    }
}

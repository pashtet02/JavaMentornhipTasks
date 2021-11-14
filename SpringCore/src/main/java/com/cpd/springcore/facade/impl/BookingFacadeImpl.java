package com.cpd.springcore.facade.impl;

import com.cpd.springcore.aspect.Loggable;
import com.cpd.springcore.facade.BookingFacade;
import com.cpd.springcore.model.Event;
import com.cpd.springcore.model.Ticket;
import com.cpd.springcore.model.User;
import com.cpd.springcore.service.EventService;
import com.cpd.springcore.service.TicketService;
import com.cpd.springcore.service.UserService;
import com.cpd.springcore.xml.TicketList;
import com.thoughtworks.xstream.XStream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookingFacadeImpl implements BookingFacade {
    @Value("${datafile.path}")
    private String datafilePath;

    private final UserService userService;
    private final EventService eventService;
    private final TicketService ticketService;
    private final XStream xStream;

    @PostConstruct
    @Loggable
    public void loadTicketsFromXml() {
        if (datafilePath != null && !datafilePath.isBlank()) {
            String xmlContent = null;
            try {
                xmlContent = new String(Files.readAllBytes(Paths.get(datafilePath)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            TicketList tickets = (TicketList) xStream.fromXML(xmlContent);
            tickets.getTickets().forEach(t -> bookTicket(t.getUserId(), t.getEventId(), t.getPlace(), t.getCategory()));
        }
    }

    @Override
    @Loggable
    public Event getEventById(long eventId) {
        return eventService.getEventById(eventId);
    }

    @Override
    @Loggable
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventService.getEventByTitle(title, pageSize, pageNum);
    }

    @Override
    @Loggable
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return eventService.getEventForDay(day, pageSize, pageNum);
    }

    @Override
    @Loggable
    public Event createEvent(Event event) {
        return eventService.save(event);
    }

    @Override
    @Loggable
    public Event updateEvent(Event event) {
        return eventService.save(event);
    }

    @Override
    @Loggable
    public boolean deleteEvent(long eventId) {
        return eventService.delete(eventId);
    }

    @Override
    @Loggable
    public User getUserById(long userId) {
        return userService.getUserById(userId);
    }

    @Override
    @Loggable
    public User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    @Override
    @Loggable
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userService.getUsersByName(name, pageSize, pageSize);
    }

    @Override
    @Loggable
    public User createUser(User user) {
        return userService.save(user);
    }

    @Override
    @Loggable
    public User updateUser(User user) {
        return userService.save(user);
    }

    @Override
    @Loggable
    public boolean deleteUser(long userId) {
        userService.delete(userId);
        return true;
    }

    @Override
    @Loggable
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        Ticket ticket = new Ticket();
        ticket.setCategory(category);
        ticket.setEventId(eventId);
        ticket.setPlace(place);
        ticket.setUserId(userId);
        return ticketService.bookTicket(ticket);
    }

    @Override
    @Loggable
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        Objects.requireNonNull(user, "User cannot be null.");

        //Check that such user exists in DB
        userService.getUserById(user.getId());

        return ticketService.getBookedTickets(user, pageSize, pageNum);
    }

    @Override
    @Loggable
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        Objects.requireNonNull(event, "Event cannot be null.");

        //Check that such event exists in DB
        eventService.getEventById(event.getId());

        return ticketService.getBookedTickets(event, pageSize, pageNum);
    }

    @Override
    @Loggable
    public boolean cancelTicket(long ticketId) {
        return ticketService.deleteTicket(ticketId);
    }
}

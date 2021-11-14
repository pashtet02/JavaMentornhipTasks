package com.cpd.springcore.facade.impl;

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
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequiredArgsConstructor
public class BookingFacadeImpl implements BookingFacade {
    @Value("${datafile.path}")
    private String datafilePath;

    private final UserService userService;
    private final EventService eventService;
    private final TicketService ticketService;
    private final XStream xStream;

    @PostConstruct
    public void loadTicketsFromXml() {
        String xmlContent = null;
        try {
            xmlContent = new String(Files.readAllBytes(Paths.get(datafilePath)));
        } catch (IOException e) {
            log.error("Unable to load tickets from {}. Message: {}", datafilePath, e.getMessage());
        }
        TicketList tickets = (TicketList) xStream.fromXML(xmlContent);
        tickets.getTickets().forEach(t -> bookTicket(t.getUserId(), t.getEventId(), t.getPlace(), t.getCategory()));
    }

    @Override
    public Event getEventById(long eventId) {

        return eventService.getEventById(eventId);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventService.getEventByTitle(title, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return eventService.getEventForDay(day, pageSize, pageNum);
    }

    @Override
    public Event createEvent(Event event) {
        log.info("Create event {}", event);
        return eventService.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        log.info("Update event {}", event);
        return eventService.save(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        log.info("Delete event by id {}", eventId);
        return eventService.delete(eventId);
    }

    @Override
    public User getUserById(long userId) {
        log.info("Get user by id {}", userId);
        return userService.getUserById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("Get user by email {}", email);
        return userService.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        log.info("Get users by name {} limit {}, {}", name, pageNum, pageSize);
        return userService.getUsersByName(name, pageSize, pageSize);
    }

    @Override
    public User createUser(User user) {
        log.info("Created user {}", user);
        return userService.save(user);
    }

    @Override
    public User updateUser(User user) {
        log.info("Update user {}", user);
        return userService.save(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        userService.delete(userId);
        log.info("Deleted user with id {}", userId);
        return true;
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        Ticket ticket = new Ticket();
        ticket.setCategory(category);
        ticket.setEventId(eventId);
        ticket.setPlace(place);
        ticket.setUserId(userId);
        log.info("Book ticket {}", ticket);
        return ticketService.bookTicket(ticket);
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        log.info("getBookedTickets() with user: {}", user);
        Objects.requireNonNull(user, "User cannot be null.");

        //Check that such user exists in DB
        userService.getUserById(user.getId());

        return ticketService.getBookedTickets(user, pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        log.info("getBookedTickets() with event: {}", event);
        Objects.requireNonNull(event, "Event cannot be null.");

        //Check that such event exists in DB
        eventService.getEventById(event.getId());

        return ticketService.getBookedTickets(event, pageSize, pageNum);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        log.info("cancelTicket() with ticketId: {}", ticketId);
        return ticketService.deleteTicket(ticketId);
    }
}

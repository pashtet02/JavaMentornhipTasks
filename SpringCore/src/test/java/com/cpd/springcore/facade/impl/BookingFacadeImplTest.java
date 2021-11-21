package com.cpd.springcore.facade.impl;

import com.cpd.springcore.config.AppConfig;
import com.cpd.springcore.facade.BookingFacade;
import com.cpd.springcore.model.Event;
import com.cpd.springcore.model.Ticket;
import com.cpd.springcore.model.User;
import com.cpd.springcore.repos.EventRepo;
import com.cpd.springcore.repos.TicketRepo;
import com.cpd.springcore.repos.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
@TestPropertySource(locations = "classpath:test.properties")
class BookingFacadeImplTest {

    @Autowired
    private ApplicationContext context;

    private BookingFacade bookingFacade;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private TicketRepo ticketRepo;

    private User user;
    private Event event;
    private Ticket ticket;

    @BeforeEach
    void setUp() {
        bookingFacade = context.getBean(BookingFacade.class);

        user = new User();
        user.setName("test");
        user.setEmail("test@test.com");

        event = new Event();
        event.setTitle("test");
        event.setDate(Date.valueOf(LocalDate.now()));

        ticket = new Ticket();
        ticket.setPlace(1);
        ticket.setCategory(Ticket.Category.PREMIUM);
        ticket.setEventId(1);
        ticket.setUserId(1);
    }

    @AfterEach
    void tearDown() {
        ticketRepo.delete(ticket);
        userRepo.delete(user);
        eventRepo.delete(event);
    }

    @Test
    void createUser() {
        user = bookingFacade.createUser(user);
        assertNotNull(user);
        assertEquals(user.getName(), user.getName());
        assertTrue(user.getId() > 0);
    }

    @Test
    void updateUser() {
        user = bookingFacade.createUser(user);
        user.setEmail("new@gmail.com");
        User updatedUser = bookingFacade.updateUser(user);
        assertEquals("new@gmail.com", updatedUser.getEmail());
    }

    @Test
    void deleteUser() {
        user = userRepo.save(user);
        assertTrue(bookingFacade.deleteUser(user.getId()));
        assertThrows(RuntimeException.class, () -> userRepo.findById(user.getId()).orElseThrow());
    }

    @Test
    void updateEvent() {
        event = bookingFacade.createEvent(event);
        event.setTitle("newTitle");
        event.setDate(Date.valueOf(LocalDate.now().plusDays(2)));
        Event updateEvent = bookingFacade.updateEvent(event);
        assertEquals("newTitle", updateEvent.getTitle());
        assertEquals(Date.valueOf(LocalDate.now().plusDays(2)), updateEvent.getDate());
    }

    @Test
    void createEvent() {
        event = bookingFacade.createEvent(event);
        assertNotNull(event);
        assertEquals(event.getTitle(), event.getTitle());
        assertTrue(event.getId() > 0);
    }

    @Test
    void deleteEvent() {
        Event createdEvent = eventRepo.save(event);
        assertTrue(bookingFacade.deleteEvent(createdEvent.getId()));
        assertThrows(RuntimeException.class, () -> eventRepo.findById(createdEvent.getId()).orElseThrow());
    }

    @Test
    void bookTicket() {
        event = bookingFacade.createEvent(event);
        user = bookingFacade.createUser(user);
        ticket = bookingFacade.bookTicket(user.getId(), event.getId(), 1, Ticket.Category.PREMIUM);
        assertEquals(user.getId(), ticket.getUserId());
        assertEquals(event.getId(), ticket.getEventId());
        assertTrue(ticket.getId() > 0);
        assertEquals(1, ticket.getPlace());
        assertEquals(Ticket.Category.PREMIUM, ticket.getCategory());
    }

    @Test
    void getBookedTicketsThrowsNullPointerExceptionWhenUserIsNull() {
        assertThrows(NullPointerException.class, () -> bookingFacade.getBookedTickets((User) null, 1, 1));
    }

    @Test
    void getBookedTicketsByUserId() {
        user = userRepo.save(user);
        List<Ticket> userTickets;
        userTickets = bookingFacade.getBookedTickets(user, 1, 0);
        assertTrue(userTickets.isEmpty());

        event = eventRepo.save(event);
        ticket = bookingFacade.bookTicket(user.getId(), event.getId(), 12, Ticket.Category.BAR);

        userTickets = bookingFacade.getBookedTickets(user, 1, 0);
        assertFalse(userTickets.isEmpty());
    }

    @Test
    void getBookedTicketsByEventId() {
        user = userRepo.save(user);
        event = eventRepo.save(event);
        List<Ticket> eventTickets;
        eventTickets = bookingFacade.getBookedTickets(event, 1, 0);
        assertTrue(eventTickets.isEmpty());
        ticket = bookingFacade.bookTicket(user.getId(), event.getId(), 12, Ticket.Category.PREMIUM);
        eventTickets = bookingFacade.getBookedTickets(event, 1, 0);
        assertFalse(eventTickets.isEmpty());
    }
}
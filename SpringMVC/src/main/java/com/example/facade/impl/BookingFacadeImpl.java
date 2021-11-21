package com.example.facade.impl;

import com.example.ecxeption.EventNotFoundException;
import com.example.facade.BookingFacade;
import com.example.model.Event;
import com.example.model.Ticket;
import com.example.model.User;
import com.example.service.EventService;
import com.example.service.TicketService;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;

@Service
@Slf4j
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
    public Page<Ticket> getBookedTickets(User user, Pageable pageable) {
        return ticketService.getTicketsByUserId(user.getId(), pageable);
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

    /*@SneakyThrows
    @Override
    public List<Ticket> preloadTickets() {

        FileReader reader = new FileReader(preloadPath);

        XStream xstream = new XStream();
        xstream.processAnnotations(TicketXml.class);
        xstream.addPermission(AnyTypePermission.ANY);
        TicketXml data = (TicketXml) xstream.fromXML(reader);
        EventMessageType event = unmarshaller.unmarshal(new StreamResource(file), EventMessagType.class).getValue();
        System.out.println(data);

        *//*ickets tickets;
        try (FileInputStream is = new FileInputStream(preloadPath)) {
            tickets = (Tickets) new XStreamMarshaller().unmarshal(new StreamSource(is));
            System.out.println("TICKETS: " + tickets);
        } catch (IOException e) {
            e.printStackTrace();
        }*//*
        return new ArrayList<>();
    }*/
}

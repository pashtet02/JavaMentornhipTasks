package com.example.facade.impl;

import com.example.aspect.Loggable;
import com.example.ecxeption.EventNotFoundException;
import com.example.facade.BookingFacade;
import com.example.model.Event;
import com.example.model.Ticket;
import com.example.model.User;
import com.example.model.UserAccount;
import com.example.service.AccountService;
import com.example.service.EventService;
import com.example.service.TicketService;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Calendar;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingFacadeImpl implements BookingFacade {

    private final EventService eventService;
    private final UserService userService;
    private final TicketService ticketService;
    private final AccountService accountService;

    @Override
    @Cacheable(value = "bookedTickets", key = "'UsersBookedTickets' + #user.getId()")
    @Loggable
    public Page<Ticket> getBookedTickets(User user, Pageable pageable) {
        return ticketService.getTicketsByUserId(user.getId(), pageable);
    }

    @Override
    @Loggable
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Ticket buyTicket(String username, long eventId) {
        User user = userService.getUserByUsername(username);
        Event event = eventService.getEvent(eventId).orElseThrow(EventNotFoundException::new);

        UserAccount account = user.getAccount();
        if (account.getMoney() > event.getPrice()) {
            account.setMoney(account.getMoney() - event.getPrice());

            accountService.updateAccount(account);

            Ticket ticket = new Ticket();
            ticket.setEvent(event);
            ticket.setUser(user);
            ticket.setCreationDate(new Date(Calendar.getInstance().getTimeInMillis()));

            return ticketService.saveTicket(ticket);
        } else throw new RuntimeException("Not enough money!");
    }

    @Override
    @Loggable
    public Ticket getDetailedTicketInfo(long eventId) {
        Ticket ticket = new Ticket();
        Event event = eventService.getEvent(eventId)
                .orElseThrow(EventNotFoundException::new);
        ticket.setEvent(event);
        return ticket;
    }

    @Override
    @Transactional
    @Loggable
    public UserAccount refillMoney(User user, Double deposit) {
        UserAccount account;
        if (user.getAccount() != null){
            account = user.getAccount();
            account.setMoney(account.getMoney() + deposit);
            account = accountService.updateAccount(account);
        } else throw new RuntimeException("User account not found!");
        return account;
    }

}

package com.cpd.springcore.service;

import com.cpd.springcore.model.Event;
import com.cpd.springcore.model.Ticket;
import com.cpd.springcore.model.User;
import com.cpd.springcore.repos.TicketRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class TicketService {
    private final TicketRepo ticketRepo;

    public Ticket bookTicket(Ticket ticket) {
        log.info("bookTicket: {}", ticket);
         return ticketRepo.save(ticket);
    }

    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        Objects.requireNonNull(event, "Cannot be null");
        return ticketRepo.findAllByEventIdOrderByEventIdAsc(event.getId(), PageRequest.of(pageNum, pageSize));
    }

    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        Objects.requireNonNull(user, "Cannot be null");
        return ticketRepo.findAllByUserIdOrderByUserIdAsc(user.getId(), PageRequest.of(pageNum, pageSize));
    }

    public boolean deleteTicket(long ticketId) {
        ticketRepo.deleteById(ticketId);
        return true;
    }
}

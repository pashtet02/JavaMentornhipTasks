package com.example.service.impl;

import com.example.model.Ticket;
import com.example.repos.TicketRepo;
import com.example.service.TicketService;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepo ticketRepo;

    public TicketServiceImpl(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepo.save(ticket);
    }
}

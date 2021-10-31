package com.example.service.impl;

import com.example.model.Ticket;
import com.example.repos.TicketRepo;
import com.example.service.TicketService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepo ticketRepo;

    public TicketServiceImpl(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    @Override
    @Transactional
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepo.save(ticket);
    }

    @Override
    public Page<Ticket> getTicketsByUserId(Long id, Pageable pageable) {
        return ticketRepo.findAllByUserId(id, pageable);
    }
}

package com.example.service;

import com.example.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface TicketService {
    @Transactional
    Ticket saveTicket(Ticket ticket);

    Page<Ticket> getTicketsByUserId(Long id, Pageable pageable);
}

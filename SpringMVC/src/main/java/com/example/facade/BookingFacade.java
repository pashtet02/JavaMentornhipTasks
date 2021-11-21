package com.example.facade;

import com.example.model.Ticket;
import com.example.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookingFacade {

    Page<Ticket> getBookedTickets(User user, Pageable pageable);

    Ticket buyTicket(String username, long eventId);

    Ticket getDetailedTicketInfo(long eventId);

}

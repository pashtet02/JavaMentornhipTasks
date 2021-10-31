package com.example.facade;

import com.example.model.Event;
import com.example.model.Ticket;
import com.example.model.User;

import java.util.List;

public interface BookingFacade {

    List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);

    Ticket buyTicket(String username, long eventId);

    Ticket getDetailedTicketInfo(long eventId);
}

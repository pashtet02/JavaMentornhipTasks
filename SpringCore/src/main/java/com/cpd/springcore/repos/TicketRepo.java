package com.cpd.springcore.repos;

import com.cpd.springcore.model.Ticket;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long> {

    List<Ticket> findAllByEventIdOrderByEventIdAsc(long eventId, Pageable page);

    List<Ticket> findAllByUserId(long userId, Pageable page);
}

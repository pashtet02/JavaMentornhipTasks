package com.cpd.springcore.repos;

import com.cpd.springcore.model.Ticket;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long> {

    List<Ticket> findAllByEventIdOrderByEventIdAsc(long eventId, PageRequest page);

    List<Ticket> findAllByUserIdOrderByUserIdAsc(long userId, PageRequest page);
}

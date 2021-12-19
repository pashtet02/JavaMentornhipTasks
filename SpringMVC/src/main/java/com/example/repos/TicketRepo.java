package com.example.repos;

import com.example.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(isolation = Isolation.DEFAULT)
public interface TicketRepo extends JpaRepository<Ticket, Long> {

    @Query(value = "SELECT t FROM Ticket t where t.user.id = ?1")
    Page<Ticket> findAllByUserId(long userId, Pageable pageable);
}

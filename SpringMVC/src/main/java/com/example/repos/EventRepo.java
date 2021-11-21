package com.example.repos;

import com.example.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional(isolation = Isolation.DEFAULT)
public interface EventRepo extends JpaRepository<Event, Long> {

    @Query("select e from Event e where e.creationDtTm <= :creationDateTime")
    List<Event> findAllWithCreationDateTimeBefore(
            @Param("creationDateTime") Date creationDateTime);

}

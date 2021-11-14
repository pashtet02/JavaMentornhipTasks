package com.cpd.springcore.repos;

import com.cpd.springcore.model.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepo extends CrudRepository<Event, Long> {
    List<Event> getEventByTitle(String title, Pageable pageRequest);

    List<Event> getEventByDate(Date day, Pageable pageRequest);
}
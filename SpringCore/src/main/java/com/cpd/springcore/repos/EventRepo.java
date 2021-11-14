package com.cpd.springcore.repos;

import com.cpd.springcore.model.Event;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepo extends CrudRepository<Event, Long> {
    List<Event> getEventByTitle(String title, PageRequest pageRequest);

    List<Event> getEventByDate(Date day, PageRequest pageRequest);
}
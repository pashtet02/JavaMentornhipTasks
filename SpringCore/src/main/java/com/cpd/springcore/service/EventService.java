package com.cpd.springcore.service;

import com.cpd.springcore.model.Event;
import com.cpd.springcore.repos.EventRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepo eventRepo;

    public Event getEventById(long eventId) {
        return eventRepo.findById(eventId).orElseThrow();
    }

    public List<Event> getEventByTitle(String title, int pageSize, int pageNum) {
        return eventRepo.getEventByTitle(title,
                PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.ASC, "title")));
    }

    public List<Event> getEventForDay(Date day, int pageSize, int pageNum) {
        return eventRepo.getEventByDate(day,
                PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.ASC, "date")));
    }

    public Event save(Event event) {
        return eventRepo.save(event);
    }

    public boolean delete(long eventId) {
        eventRepo.deleteById(eventId);
        return true;
    }
}

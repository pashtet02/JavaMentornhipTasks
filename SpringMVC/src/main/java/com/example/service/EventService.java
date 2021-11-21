package com.example.service;

import com.example.model.Event;
import com.example.repos.EventRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class EventService {
    private final EventRepo eventRepo;

    public EventService(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    @Cacheable("events")
    public Iterable<Event> getEvents() {
        log.info("Get all events");
        return eventRepo.findAll();
    }

    public Event save(Event event) {
        log.info("Created event {}", event);
        return eventRepo.save(event);
    }

    public Optional<Event> getEvent(long eventId){
        return eventRepo.findById(eventId);
    }
}

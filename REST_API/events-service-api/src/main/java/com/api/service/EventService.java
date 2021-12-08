package com.api.service;

import com.api.model.Event;
import com.example.dto.EventDto;

import java.util.List;

public interface EventService {

    Event createEvent(EventDto event);
    Event updateEvent(EventDto event);
    Event getEvent(long eventId);
    void deleteEvent(long eventId);
    List<Event> getAllEvents();
    List<Event> getAllEventsByTitle(String title);
}

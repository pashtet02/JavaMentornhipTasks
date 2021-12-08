package com.impl.service.impl;

import com.example.dto.EventDto;
import com.api.model.Event;
import com.impl.model.EventImpl;
import com.impl.repos.EventRepo;
import com.api.service.EventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepo eventRepo;

    public EventServiceImpl(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    @Override
    public Event createEvent(EventDto eventDto) {
        return eventRepo.save(toEventImpl(eventDto));
    }

    @Override
    public Event updateEvent(EventDto eventDto) {
        return eventRepo.save(toEventImpl(eventDto));
    }

    @Override
    public Event getEvent(long eventId) {
        return eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found!"));
    }

    @Override
    public void deleteEvent(long eventId) {
        eventRepo.deleteById(eventId);
    }

    @Override
    public List<Event> getAllEvents() {
        List<EventImpl> allByTitle = (List<EventImpl>) eventRepo.findAll();
        return new ArrayList<>(allByTitle);
    }

    @Override
    public List<Event> getAllEventsByTitle(String title) {
        List<EventImpl> allByTitle = eventRepo.findAllByTitle(title);
        return new ArrayList<>(allByTitle);
    }

    private EventDto toEventDto(EventImpl event){
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setPlace(event.getPlace());
        eventDto.setSpeaker(event.getSpeaker());
        eventDto.setTitle(event.getTitle());
        eventDto.setEventType(event.getEventType());
        eventDto.setDateTime(event.getDateTime());
        return eventDto;
    }

    private EventImpl toEventImpl(EventDto event){
        EventImpl eventimpl = new EventImpl();
        eventimpl.setId(event.getId());
        eventimpl.setPlace(event.getPlace());
        eventimpl.setSpeaker(event.getSpeaker());
        eventimpl.setTitle(event.getTitle());
        eventimpl.setEventType(event.getEventType());
        eventimpl.setDateTime(event.getDateTime());
        return eventimpl;
    }
}

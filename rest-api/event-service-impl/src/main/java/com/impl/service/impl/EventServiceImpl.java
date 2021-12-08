package com.impl.service.impl;

import com.example.dto.EventDto;
import com.impl.mapper.ModelImplMapper;
import com.api.model.Event;
import com.impl.model.EventImpl;
import com.impl.repos.EventRepo;
import com.api.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepo eventRepo;

    public EventServiceImpl(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    @Override
    public Event createEvent(EventDto eventDto) {
        return eventRepo.save(ModelImplMapper.INSTANCE.eventDtoToEventImpl(eventDto));
    }

    @Override
    public Event updateEvent(EventDto eventDto) {
        return eventRepo.save(ModelImplMapper.INSTANCE.eventDtoToEventImpl(eventDto));
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
        return allByTitle.stream().map(ModelImplMapper.INSTANCE::eventImplToEvent).collect(Collectors.toList()) ;
    }

    @Override
    public List<Event> getAllEventsByTitle(String title) {
        List<EventImpl> allByTitle = eventRepo.findAllByTitle(title);
        return allByTitle.stream().map(ModelImplMapper.INSTANCE::eventImplToEvent).collect(Collectors.toList()) ;
    }
}

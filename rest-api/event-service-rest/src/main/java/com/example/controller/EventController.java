package com.example.controller;

import com.api.model.Event;
import com.api.service.EventService;
import com.example.dto.EventDto;
import com.impl.service.impl.EventServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SpringBootApplication(scanBasePackages = "com.example")
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping()
    public List<Event> getEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping
    public Event createEvent(@RequestBody EventDto eventDto){
        return eventService.createEvent(eventDto);
    }

    public static void main(String[] args) {
        SpringApplication.run(EventController.class, args);
    }
}

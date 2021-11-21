package com.example.controller;

import com.example.model.Event;
import com.example.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping()
    public String main(Model model) {
        Iterable<Event> events = eventService.getEvents();
        model.addAttribute("events", events);
        return "events";
    }

    @PostMapping
    public String redirect(){
        return "redirect:/events";
    }

    @PostMapping("/create")
    public String add(@RequestParam String name,
                      @RequestParam String details,
                      @RequestParam String location,
                      @RequestParam Double price) {
        Event event = new Event();
        event.setName(name);
        event.setPrice(price);
        event.setLocation(location);
        event.setDetails(details);
        eventService.save(event);
        log.info("Event {} saved successfully", event);
        return "redirect:/events";
    }
}
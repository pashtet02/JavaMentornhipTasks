package com.impl;

import com.api.model.Event;
import com.api.service.EventService;
import com.example.dto.EventDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class EventServiceImplApp {
    public static void main(String[] args) {
        SpringApplication.run(EventServiceImplApp.class, args);
    }

    @Bean
    public CommandLineRunner run(EventService eventService) {
        return args -> {
            EventDto eventDto = new EventDto();
                    eventDto.setPlace(15);
                    eventDto.setTitle("test");
            Event event = eventService.createEvent(eventDto);
            eventService.createEvent(eventDto);
            eventService.createEvent(eventDto);
            eventService.createEvent(eventDto);
            System.out.println(eventService.getAllEvents());
        };
    }
}



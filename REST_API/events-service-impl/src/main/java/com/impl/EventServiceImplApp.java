package com.impl;

import com.api.model.Event;
import com.api.service.EventService;
import com.example.dto.EventDto;
import com.impl.repos.EventRepo;
import com.impl.service.impl.EventServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackageClasses = {EventRepo.class, EventService.class, EventServiceImpl.class})
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



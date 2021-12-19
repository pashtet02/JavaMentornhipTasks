package com.example.controller;

import com.api.model.Event;
import com.api.service.EventService;
import com.example.dto.EventDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("/api")
@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    @ApiOperation(value = "Gets ALL events from the database.",
            notes = "Certainly we need pagination here but I`m too tired to do it right now :)",
            response = List.class)
    public CollectionModel<Event> getEvents() {

        List<Event> allEvents = eventService.getAllEvents();

        for (Event event : allEvents) {
            long eventId = event.getId();
            Link selfLink = linkTo(EventController.class).slash(eventId).withSelfRel();
            event.add(selfLink);

            Link eventsLink = linkTo(methodOn(EventController.class)
                    .getEvents()).withRel("allEvents");
            event.add(eventsLink);
        }

        Link link = linkTo(EventController.class).withSelfRel();

        return CollectionModel.of(allEvents, link);
    }

    @GetMapping("/event/{eventId}")
    @ApiOperation(value = "Finds Event by id.",
            notes = "Provide a positive id to retrieve Event",
            response = Event.class)
    public ResponseEntity<Event> getEvent(@PathVariable long eventId) {
        return ResponseEntity.ok().body(eventService.getEvent(eventId));
    }

    @PostMapping("/event")
    @ApiResponses(value = {@ApiResponse(code = 201,
            examples = @Example(value = {@ExampleProperty(value = "title", mediaType = "string"),
                    @ExampleProperty(value = "speaker", mediaType = "string")}),
            message = "When Event created successfully")})
    @ApiOperation(value = "Creates an event",
            notes = "Provide a Event as a response body",
            response = Event.class)
    public ResponseEntity<Event> createEvent(@RequestBody EventDto eventDto) {
        Event createdEvent = eventService.createEvent(eventDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(createdEvent);
    }

    @PutMapping("/event/{eventId}")
    @ApiOperation(value = "Updates an event",
            notes = "Provide a Event id to update its fields",
            response = Event.class)
    public ResponseEntity<Event> updateEvent(@RequestBody EventDto eventDto) {
        return ResponseEntity.ok().body(eventService.updateEvent(eventDto));
    }

    @DeleteMapping("/event/{eventId}")
    @ApiOperation(value = "Deletes an event",
            notes = "Provide a Event id to delete it")
    public ResponseEntity<Void> deleteEvent(@PathVariable long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.noContent().build();
    }
}

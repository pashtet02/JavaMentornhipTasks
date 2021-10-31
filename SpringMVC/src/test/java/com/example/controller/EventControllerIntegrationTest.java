package com.example.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EventControllerIntegrationTest {

    @Autowired
    private EventController eventController;

    @Test
    void main() {
    }

    @Test
    void redirect() {
        String outcome = eventController.redirect();
        assertEquals("redirect:/events", outcome);
    }

    @Test
    @DisplayName("Test successful workflow of adding new event")
    void add() {
            double price = 36.6;
        String name = "test";
        String details = "test";
        String location = "test";

        String outcome = eventController.add(name, location, details, price);
        assertEquals("redirect:/events", outcome);
    }

}
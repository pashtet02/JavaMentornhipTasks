package com.example.controller;


import com.example.service.EventService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EventController.class)
public class EventControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService mockEventService;

    @InjectMocks
    private EventController eventController;

}

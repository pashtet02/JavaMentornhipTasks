package com.example.repos;

import com.example.model.Event;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class EventRepoTest {

    @Autowired
    private EventRepo eventRepo;

    @Test
    @Disabled
    void findAllWithCreationDateTimeBefore() throws Exception {


            List<Event> result = eventRepo.findAllWithCreationDateTimeBefore(
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2017-12-15 10:00"));

            assertEquals(2, result.size());
            assertTrue(result.stream()
                    .map(Event::getId)
                    .allMatch(id -> Arrays.asList(2, 3).contains(id)));
    }
}
package com.example.springboot.repos;

import com.example.springboot.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class UserRepoTest {

    private static final String TEST = "test";

    @Autowired
    private UserRepo userRepo;

    @AfterEach
    void tearDown() {
        userRepo.deleteAll();
    }

    @Test
    void findByUsernameSuccess() {
        User expectedUser = User.builder()
                .username(TEST)
                .email(TEST)
                .age(15)
                .build();

        userRepo.save(expectedUser);

        User actualUser = userRepo.findByUsername(TEST);

        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);
        assertThat(actualUser.getUsername()).isEqualTo(TEST);
        assertThat(actualUser.getEmail()).isEqualTo(TEST);
        assertThat(actualUser.getAge()).isEqualTo(15);
    }
}
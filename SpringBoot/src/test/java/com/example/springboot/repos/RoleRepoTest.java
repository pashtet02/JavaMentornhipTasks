package com.example.springboot.repos;

import com.example.springboot.domain.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoleRepoTest {

    private static final String TEST = "test";

    @Autowired
    private RoleRepo roleRepo;

    @AfterEach
    void tearDown() {
        roleRepo.deleteAll();
    }

    @Test
    void findByNameSuccess() {
        Role expectedRole = Role.builder().name(TEST).build();
        roleRepo.save(expectedRole);

        Role actualRole = roleRepo.findByName(TEST);
        assertNotNull(actualRole);
        assertEquals(expectedRole, actualRole);
    }

    @Test
    void findByNameReturnNullIfNameDoesNotExists() {
        Role actualRole = roleRepo.findByName(TEST);
        assertNull(actualRole);
    }
}
/*
package com.example.springboot.service.impl;

import com.example.springboot.domain.Role;
import com.example.springboot.domain.User;
import com.example.springboot.repos.RoleRepo;
import com.example.springboot.repos.UserRepo;
import com.example.springboot.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DataJpaTest
@Disabled
class UserServiceImplIntegrationTest {

    @Autowired
    private PasswordEncoder mockPasswordEncoder;

    @Autowired
    private UserService userService;

    private User user;
    private Set<Role> roles;

    @BeforeEach
    void setUp() {
        roles = new HashSet<>();
        Role role = Role.builder().id(1L).name("testRole").build();
        roles.add(role);
        user = User.builder()
                .username("test")
                .age(15)
                .email("test")
                .roles(roles)
                .password("pass")
                .build();
    }

    @AfterEach
    void tearDown() {
        user = null;
        roles = null;
    }

    @Test
    void saveUser() {
        when(mockPasswordEncoder.encode(anyString())).thenReturn("pass");
        when(mockUserRepo.save(any(User.class))).thenReturn(user);
        User actualUser = userService.saveUser(this.user);

        verify(mockPasswordEncoder, times(1)).encode(anyString());
        verify(mockUserRepo, times(1)).save(user);

        assertEquals(user, actualUser);
        assertEquals("pass", actualUser.getPassword());
        assertEquals(roles.size(), user.getRoles().size());
    }

    @Test
    @DisplayName("Save User Will Throw Null Pointer Exception When Null User Is Passed")
    void saveUserWillThrowNullPointerExceptionWhenNullUserIsPassed() {
        assertThrows(NullPointerException.class, () -> userService.saveUser(null));
    }

    @Test
    void addRoleToUser() {
        Role adminRole = Role.builder().name("admin").id(1L).build();
        when(mockRoleRepo.findByName(anyString())).thenReturn(adminRole);
        when(mockUserRepo.findByUsername(anyString())).thenReturn(user);
        when(mockUserRepo.save(any(User.class))).thenReturn(user);

        assertEquals(1, user.getRoles().size());

        userService.addRoleToUser(user.getUsername(), "admin");

        assertEquals(2, user.getRoles().size());

        verify(mockUserRepo, times(1)).findByUsername("test");
        verify(mockRoleRepo, times(1)).findByName("admin");
        verify(mockUserRepo, times(1)).save(user);
    }

    @Test
    void getUser() {
        when(mockUserRepo.findByUsername(user.getUsername())).thenReturn(user);

        User serviceUser = userService.getUser(this.user.getUsername());

        assertEquals(serviceUser, user);
        verify(mockUserRepo, times(1)).findByUsername(user.getUsername());
    }

    @Test
    void loadUserByUsername() {
        when(mockUserRepo.findByUsername(user.getUsername())).thenReturn(user);

        UserDetails userDetails = userService.loadUserByUsername(this.user.getUsername());

        assertEquals(userDetails.getUsername(), user.getUsername());
        assertEquals(userDetails.getPassword(), user.getPassword());
        verify(mockUserRepo, times(1)).findByUsername(user.getUsername());
    }

    @Test
    void loadUserByUsernameThrowsExceptionWhenUserIsNull() {
        when(mockUserRepo.findByUsername(anyString())).thenReturn(null);
        UsernameNotFoundException exc =
                assertThrows(UsernameNotFoundException.class,
                        () -> userService.loadUserByUsername("hello"));
        assertTrue(exc.getMessage().contains("User not found!"));
    }
}
*/

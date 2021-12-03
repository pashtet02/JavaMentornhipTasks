package com.example.springboot.api;

import com.example.springboot.domain.User;
import com.example.springboot.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest({UserController.class})
/*@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UserController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@AutoConfigureMockMvc(addFilters = true)
@Import(SecurityConfig.class)*/
class UserControllerTest {

    private User user;

    @Autowired
    private UserDetailsService userDetailsService;

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        user = User.builder().username("bob").password("pass").email("test@test.com").age(14).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @WithMockUser(username = "test", roles = {"ROLE_ADMIN", "ROLE_USER"})
    void getUsers() throws Exception {
        when(userService.getUsers()).thenReturn(List.of(user));
        userService.saveUser(user);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value("bob"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("test@test.com"));
    }
}
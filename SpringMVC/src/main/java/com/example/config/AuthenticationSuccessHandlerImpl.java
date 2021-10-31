package com.example.config;

import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;

@Component
@Slf4j
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private final UserService userService;

    public AuthenticationSuccessHandlerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String userName;
        if (authentication.getPrincipal() instanceof Principal) {
            userName = ((Principal) authentication.getPrincipal()).getName();

        } else {
            userName = ((User) authentication.getPrincipal()).getUsername();
        }
        log.info("userName: " + userName);
        HttpSession session = request.getSession();
        session.setAttribute("username", userName);
        com.example.model.User user = userService.getUserByUsername(userName);
        session.setAttribute("user", user);
        request.getRequestDispatcher("/events").forward(request, response);
    }
}
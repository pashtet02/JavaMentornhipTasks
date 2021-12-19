package com.example.controller;

import com.example.facade.BookingFacade;
import com.example.model.UserAccount;
import com.example.model.enums.Role;
import com.example.model.User;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final BookingFacade bookingFacade;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user,
                          @RequestParam(required = false) Double deposit, Model model) {
        User userFromDb = userService.getUserByUsername(user.getUsername());

        if (userFromDb != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userService.addUser(user, deposit);
        return "redirect:/login";
    }
}
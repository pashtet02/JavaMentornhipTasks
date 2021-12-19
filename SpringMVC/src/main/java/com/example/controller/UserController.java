package com.example.controller;

import com.example.facade.BookingFacade;
import com.example.model.User;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BookingFacade facade;

    @GetMapping
    public String userList(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "userList";
    }

    @PostMapping("/deposit")
    public String addMoney(
            @RequestParam(name = "deposit") Double deposit,
            HttpSession session){
        User user = (User) session.getAttribute("user");
        facade.refillMoney(user, deposit);
        return "redirect:/events";
    }
}

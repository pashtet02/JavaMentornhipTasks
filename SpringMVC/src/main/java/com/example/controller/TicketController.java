package com.example.controller;

import com.example.facade.BookingFacade;
import com.example.model.Ticket;
import com.example.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/ticket")
public class TicketController {
    private final BookingFacade bookingFacade;

    public TicketController(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @GetMapping()
    public String getTicketInfo(@RequestParam long eventId, Model model, HttpSession session){
        log.info("Ticket get mapping on the page ticket info");
        Ticket ticketInfo = bookingFacade.getDetailedTicketInfo(eventId);
        model.addAttribute("ticket", ticketInfo);
        System.out.println("USERNAME: " + session.getAttribute("username"));
        return "ticketInfo";
    }

    @PostMapping("/purchase")
    public String buyTicket(@RequestParam long eventId, Model model, HttpSession session){
        String username = (String) session.getAttribute("username");
        System.out.println("User in buy ticket " +  username);
        System.out.println("USERNAME FROM SESSION " + username);
        Ticket ticket = bookingFacade.buyTicket(username, eventId);
         model.addAttribute("ticket", ticket);
        return "success";
    }
}

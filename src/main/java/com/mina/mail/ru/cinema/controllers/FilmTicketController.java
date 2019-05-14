package com.mina.mail.ru.cinema.controllers;

import com.mina.mail.ru.cinema.service.impl.FilmTicketService;
import com.mina.mail.ru.cinema.service.util.UserOrder;
import com.mina.mail.ru.cinema.service.util.UserTickets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by Mina on 24.04.2019.
 */

@RestController
public class FilmTicketController {

    @Autowired
    private FilmTicketService filmTicketService;

    @PostMapping(value = "/makeOrder")
    public void createOrder (@RequestBody UserOrder order, Principal principal) {
        filmTicketService.createOrder(order, principal.getName());
    }

    @GetMapping(value = "listOrders", params = "login")
    public List<UserTickets> getAllOrders (@RequestParam("login") String login) {
       List<UserTickets> filmTicketDbos = filmTicketService.getOrders(login);
        return filmTicketDbos;
    }

    @GetMapping(value = "/delete", params = {"title", "date", "seat", "login"})
    public List<UserTickets> deleteOrder(@RequestParam("title") String title,
                            @RequestParam("date") Integer date,
                            @RequestParam("seat") Integer seat,
                            @RequestParam("login") String login) {
       filmTicketService.deleteOrder(title, date, seat);
       return filmTicketService.getOrders(login);

    }
}

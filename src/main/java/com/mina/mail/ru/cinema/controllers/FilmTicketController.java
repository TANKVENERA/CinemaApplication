package com.mina.mail.ru.cinema.controllers;

import com.mina.mail.ru.cinema.service.impl.FilmTicketService;
import com.mina.mail.ru.cinema.service.util.UserOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by Mina on 24.04.2019.
 */

@RestController
public class FilmTicketController {

    private FilmTicketService filmTicketService;

    public FilmTicketController(FilmTicketService ticketService) {
        this.filmTicketService = ticketService;
    }

    @PostMapping(value = "/makeOrder")
    public void createOrder (@RequestBody UserOrder order, Principal principal) {
        filmTicketService.createOrder(order, principal.getName());
    }
}

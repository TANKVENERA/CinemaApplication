package com.mina.mail.ru.cinema.controller;

import com.mina.mail.ru.cinema.service.FilmTicketService;
import com.mina.mail.ru.cinema.dto.UserOrder;
import com.mina.mail.ru.cinema.dto.UserTickets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by Mina on 24.04.2019.
 */

@RestController
public class FilmTicketController {

    private FilmTicketService filmTicketService;

    @Autowired
    public FilmTicketController(FilmTicketService filmTicketService) {
        this.filmTicketService = filmTicketService;
    }

    @PostMapping(value = "/makeOrder")
    public void createOrder (@RequestBody UserOrder order, Principal principal) {
        filmTicketService.createOrder(order, principal.getName());
    }

    @GetMapping(value = "listOrders", params = "login")
    public ResponseEntity<List<UserTickets>> getAllOrders (@RequestParam("login") String login) {
        return ResponseEntity.status(HttpStatus.OK).body(filmTicketService.getOrders(login));
    }

    @GetMapping(value = "/delete", params = {"title", "date", "seat", "login"})
    public ResponseEntity<List<UserTickets>> deleteOrder(@RequestParam("title") String title,
                            @RequestParam("date") Integer date,
                            @RequestParam("seat") Integer seat,
                            @RequestParam("login") String login) {
       filmTicketService.deleteOrder(title, date, seat);
       return ResponseEntity.status(HttpStatus.OK).body(filmTicketService.getOrders(login));

    }
}

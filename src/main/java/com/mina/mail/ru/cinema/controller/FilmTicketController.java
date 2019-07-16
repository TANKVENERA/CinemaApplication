package com.mina.mail.ru.cinema.controller;

import com.mina.mail.ru.cinema.service.FilmTicketService;
import com.mina.mail.ru.cinema.dto.UserOrder;
import com.mina.mail.ru.cinema.dto.UserSeat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(FilmTicketController.class);
    private FilmTicketService filmTicketService;

    @Autowired
    public FilmTicketController(FilmTicketService filmTicketService) {
        this.filmTicketService = filmTicketService;
    }

    @PostMapping(value = "/save")
    public void createOrder (@RequestBody UserOrder order, Principal principal) {
        logger.info("Trying to save user order...");
        filmTicketService.createOrder(order, principal.getName());
    }

    @PostMapping(value = "/update")
    public void updateOrder (@RequestBody UserOrder order, Principal principal) {
        logger.info("Trying to update user order...");
        filmTicketService.updateOrder(order, principal.getName());
    }

    @GetMapping(value = "/listorders", params = "login")
    public ResponseEntity<List<UserSeat>> getAllOrders (@RequestParam("login") String login) {
        logger.info("Retrieving user orders...");
        return ResponseEntity.status(HttpStatus.OK).body(filmTicketService.getOrders(login));
    }

    @GetMapping(value = "/delete", params = {"ticket", "login"})
    public ResponseEntity<List<UserSeat>> deleteOrder(@RequestParam("ticket") String ticket,
                                                      @RequestParam("login") String login) {
        logger.info("Trying to delete order...");
       filmTicketService.deleteOrderByTicket(ticket);
       return ResponseEntity.status(HttpStatus.OK).body(filmTicketService.getOrders(login));

    }
}

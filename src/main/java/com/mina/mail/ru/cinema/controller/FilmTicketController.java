package com.mina.mail.ru.cinema.controller;

import com.mina.mail.ru.cinema.service.FilmTicketService;
import com.mina.mail.ru.cinema.dto.UserOrder;
import com.mina.mail.ru.cinema.dto.UserSeatDto;
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
    private final FilmTicketService filmTicketService;

    @Autowired
    public FilmTicketController(final FilmTicketService filmTicketService) {
        this.filmTicketService = filmTicketService;
    }

    @PostMapping(value = "/orders")
    public void createOrder (@RequestBody final UserOrder order, final Principal principal) {
        logger.info("Trying to save user order...");
        filmTicketService.createOrder(order, principal.getName());
    }

    @PutMapping(value = "/orders")
    public void updateOrder (@RequestBody final UserOrder order, final Principal principal) {
        logger.info("Trying to update user order...");
        filmTicketService.updateOrder(order, principal.getName());
    }

    @GetMapping(value = "/orders", params = "login")
    public ResponseEntity<List<UserSeatDto>> getAllOrders (@RequestParam("login") final String login) {
        logger.info("Retrieving user orders...");
        return ResponseEntity.status(HttpStatus.OK).body(filmTicketService.getOrders(login));
    }

    @DeleteMapping("/orders/{ticket}")
    public ResponseEntity<List<UserSeatDto>> deleteOrder(@PathVariable("ticket") final String ticket, final Principal principal) {
       logger.info("Trying to delete order...");
       filmTicketService.deleteOrderByTicket(ticket);
       return ResponseEntity.status(HttpStatus.OK).body(filmTicketService.getOrders(principal.getName()));

    }
}

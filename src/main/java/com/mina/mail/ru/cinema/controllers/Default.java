package com.mina.mail.ru.cinema.controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mina on 21.04.2019.
 */

@RestController
public class Default {

    @RequestMapping("/")
    public String start() {
        return "HI!";
    }
}

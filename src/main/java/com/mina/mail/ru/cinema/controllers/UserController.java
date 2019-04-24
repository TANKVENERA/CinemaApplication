package com.mina.mail.ru.cinema.controllers;

import com.mina.mail.ru.cinema.repository.dao.VisitorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mina on 21.04.2019.
 */

@RestController
public class VisitorController {

    @Autowired
    private VisitorDAO visitor;

    public VisitorController() {
    }

    @RequestMapping("/")
    public String start() {
        Long count = visitor.count();
        return "HI number of users - " + count;
    }
}

package com.mina.mail.ru.cinema.controllers;

import com.mina.mail.ru.cinema.repository.dbo.UserDbo;
import com.mina.mail.ru.cinema.repository.impl.UserDAO;
import com.mina.mail.ru.cinema.service.dto.UserDto;
import com.mina.mail.ru.cinema.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Mina on 21.04.2019.
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    public UserController() {
    }

    @GetMapping("/")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
}

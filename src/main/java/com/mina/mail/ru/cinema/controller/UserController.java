package com.mina.mail.ru.cinema.controller;

import com.mina.mail.ru.cinema.dto.UserDto;
import com.mina.mail.ru.cinema.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;


/**
 * Created by Mina on 21.04.2019.
 */

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/checkauth")
    public ResponseEntity<UserDto> checkAuth(Authentication auth) {
        logger.info("Checking if user is signed in...");
        UserDto userDto = new UserDto();
        userDto.setLogin(auth == null ? "" : auth.getName());
        logger.info(auth == null ? "user is not in system" : "user is signed in");
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    /** Used for test purposes**/
    @GetMapping("/")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/login")
    public ResponseEntity<UserDto> login(Authentication auth) {
        UserDto userDto = new UserDto();
        userDto.setLogin(auth.getName());
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @GetMapping(value = "/signout")
    public ResponseEntity<UserDto> logout(HttpServletRequest request) {
        HttpSession session;
        SecurityContextHolder.clearContext();
        session= request.getSession(false);
        if(session != null) {
            logger.info("Invalidating user session...");
            session.invalidate();
        }
        for(Cookie cookie : request.getCookies()) {
            logger.info("Deleting  user session id...");
            cookie.setMaxAge(0);
        }
        UserDto userDto = new UserDto();
        userDto.setLogin("");
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @GetMapping(value = "/register", params = "login")
    public ResponseEntity<String> createUser (@RequestParam("login") String login) {
        UserDto userDto = new UserDto(login, "USER");
        userDto.setLogin(login);
        logger.info("Trying to save new user...");
        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(userDto));
    }
}

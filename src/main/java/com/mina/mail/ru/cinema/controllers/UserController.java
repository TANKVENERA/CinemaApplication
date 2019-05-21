package com.mina.mail.ru.cinema.controllers;


import com.mina.mail.ru.cinema.service.dto.UserDto;
import com.mina.mail.ru.cinema.service.impl.UserService;
import com.mina.mail.ru.cinema.service.dto.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/checkauth")
    public CurrentUser pingUser() {
      CurrentUser currentUser = new CurrentUser();
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      boolean isAnonymous = authentication instanceof AnonymousAuthenticationToken;
      currentUser.setLoggedInUser(isAnonymous  ? "" : authentication.getName());
      currentUser.setAuthenticated(isAnonymous ? false : true);
      return currentUser;
    }

    @GetMapping("/")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/login")
    public CurrentUser login() {
        String loggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean authenticated = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        return new CurrentUser(loggedInUser, authenticated);
    }

    @GetMapping(value = "/signout")
    public CurrentUser logout(HttpServletRequest request) {
        HttpSession session;
        SecurityContextHolder.clearContext();
        session= request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        for(Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new CurrentUser("none", authentication == null ? false:true );
    }

    @GetMapping(value = "/register", params = "login")
    public String createUser (@RequestParam("login") String login) {
        UserDto userDto = new UserDto(login, "USER");
        userDto.setLogin(login);
        return userService.createUser(userDto);
    }
}

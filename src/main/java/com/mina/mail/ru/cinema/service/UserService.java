package com.mina.mail.ru.cinema.service;

import com.mina.mail.ru.cinema.dbo.UserEntity;
import com.mina.mail.ru.cinema.repository.UserDAO;
import com.mina.mail.ru.cinema.converter.UserConverter;
import com.mina.mail.ru.cinema.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mina on 23.04.2019.
 */

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserDAO userDAO;
    private UserConverter userConverter;

    @Autowired
    public UserService(UserDAO userDAO, UserConverter userConverter) {
        this.userDAO = userDAO;
        this.userConverter = userConverter;
    }

    public UserService() {
    }

    public List<UserDto> getAllUsers() {
        List<UserDto> usersDto = new ArrayList<>();
        List<UserEntity> usersEntity = userDAO.findAll();

        for (UserEntity d : usersEntity) {
            usersDto.add(userConverter.convertToDto(d));
        }
      return usersDto;
    }

    public UserDto getUser(String login) {
        UserEntity userEntity = userDAO.getUserByName(login);
        UserDto userDto = userConverter.convertToDto(userEntity);
        return userDto;
    }

    public String createUser(String login) {
        UserDto userDto = new UserDto(login, "USER");
        userDto.setLogin(login);
        UserEntity userEntity = userConverter.convertToDbo(userDto);
        UserEntity existedUser = userDAO.getUserByName(userDto.getLogin());
        if (existedUser != null) {
            logger.warn("User already exists!");
            return "User already exists!";
        }
        else {
            userDAO.save(userEntity);
            logger.info("User was saved");
            return "User was created successfully!";
        }
    }

    public UserDto checkAuthentication (Authentication auth) {
        UserDto userDto = new UserDto();
        userDto.setLogin(auth == null ? "" : auth.getName());
        logger.info(auth == null ? "user is not in system" : "user is signed in");
        return userDto;
    }

    public UserDto logout (HttpServletRequest request) {
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
        return userDto;
    }

    /**For test purposes**/
    public void deleteUser (String login) {
        UserEntity user = userDAO.getUserByName(login);
        userDAO.delete(userDAO.getUserByName(login));
    }

}

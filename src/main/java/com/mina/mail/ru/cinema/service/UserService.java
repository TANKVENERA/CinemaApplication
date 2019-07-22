package com.mina.mail.ru.cinema.service;

import com.mina.mail.ru.cinema.dbo.UserEntity;
import com.mina.mail.ru.cinema.repository.UserRepository;
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
    private UserRepository userRepository;
    private UserConverter userConverter;

    @Autowired
    public UserService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public UserService() {
    }

    public List<UserDto> getAllUsers() {
        final List<UserDto> usersDto = new ArrayList<>();
        List<UserEntity> usersEntity = userRepository.findAll();

        for (UserEntity d : usersEntity) {
            usersDto.add(userConverter.convertToDto(d));
        }
      return usersDto;
    }

    public UserDto getUser(String login) {
        UserEntity userEntity = userRepository.getUserByName(login);
        final UserDto userDto = userConverter.convertToDto(userEntity);
        return userDto;
    }

    public String createUser(String login) {
        final UserDto userDto = new UserDto(login, "USER");
        userDto.setLogin(login);
        UserEntity userEntity = userConverter.convertToDbo(userDto);
        UserEntity existedUser = userRepository.getUserByName(userDto.getLogin());
        if (existedUser != null) {
            logger.warn("User already exists!");
            return "User already exists!";
        }
        else {
            userRepository.save(userEntity);
            logger.info("User was saved");
            return "User was created successfully!";
        }
    }

    public UserDto checkAuthentication (Authentication auth) {
        final UserDto userDto = new UserDto();
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
        final UserDto userDto = new UserDto();
        userDto.setLogin("");
        return userDto;
    }

    /**For test purposes**/
    public void deleteUser (String login) {
        UserEntity user = userRepository.getUserByName(login);
        userRepository.delete(userRepository.getUserByName(login));
    }

}

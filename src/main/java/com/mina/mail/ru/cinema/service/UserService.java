package com.mina.mail.ru.cinema.service;

import com.mina.mail.ru.cinema.entity.UserEntity;
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
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Autowired
    public UserService(final UserRepository userRepository, final UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public List<UserDto> getAllUsers() {
        final List<UserDto> usersDto = new ArrayList<>();
        final List<UserEntity> usersEntity = userRepository.findAll();

        for (UserEntity d : usersEntity) {
            usersDto.add(userConverter.convertToDto(d));
        }
      return usersDto;
    }

    public UserDto getUser(final String login) {
        final UserEntity userEntity = userRepository.getUserByName(login);
        final UserDto userDto = userConverter.convertToDto(userEntity);
        return userDto;
    }

    public String createUser(final String login) {
        final UserDto userDto = new UserDto(login, "USER");
        userDto.setLogin(login);
        final UserEntity userEntity = userConverter.convertToDbo(userDto);
        final UserEntity existedUser = userRepository.getUserByName(userDto.getLogin());
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

    public UserDto checkAuthentication (final Authentication auth) {
        final UserDto userDto = new UserDto();
        if (auth == null) {
            userDto.setLogin("");
        }
        else {
            userDto.setLogin(auth.getName());
            if (checkAdminAuthority(auth)) {
                userDto.setRole("ADMIN");
            }
        }
        logger.info(auth == null ? "user is not in system" : "user is signed in");
        return userDto;
    }

    public UserDto logout (final HttpServletRequest request) {
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
    public void deleteUser (final String login) {
        userRepository.delete(userRepository.getUserByName(login));
    }

    public UserDto login(final Authentication auth) {
        final UserDto userDto = new UserDto();
        userDto.setLogin(auth.getName());
        if (checkAdminAuthority(auth)) {
            userDto.setRole("ADMIN");
        }
        return userDto;
    }

    public boolean checkAdminAuthority (final Authentication auth) {
        return auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        }
}

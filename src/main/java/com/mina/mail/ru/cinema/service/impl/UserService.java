package com.mina.mail.ru.cinema.service.impl;

import com.mina.mail.ru.cinema.repository.dbo.UserDbo;
import com.mina.mail.ru.cinema.repository.impl.UserDAO;
import com.mina.mail.ru.cinema.service.converter.UserConverter;
import com.mina.mail.ru.cinema.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mina on 23.04.2019.
 */

@Service
public class UserService {

    private UserDAO userDAO;
    private UserConverter userConverter;

    @Autowired
    public UserService(UserDAO userDAO, UserConverter userConverter) {
        this.userDAO = userDAO;
        this.userConverter = userConverter;
    }


    public List<UserDto> getAllUsers() {
        List<UserDto> usersDto = new ArrayList<>();
        List<UserDbo> usersDbo = userDAO.findAll();

        for (UserDbo d : usersDbo) {
            usersDto.add(userConverter.convertToDto(d));
        }

      return usersDto;
    }
}

package com.mina.mail.ru.cinema.service;

import com.mina.mail.ru.cinema.dbo.UserEntity;
import com.mina.mail.ru.cinema.repository.UserDAO;
import com.mina.mail.ru.cinema.converter.UserConverter;
import com.mina.mail.ru.cinema.dto.UserDto;
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

    public UserService() {
    }

    public List<UserDto> getAllUsers() {
        List<UserDto> usersDto = new ArrayList<>();
        List<UserEntity> usersDbo = userDAO.findAll();

        for (UserEntity d : usersDbo) {
            usersDto.add(userConverter.convertToDto(d));
        }
      return usersDto;
    }

    public UserDto getUser(String login) {
        UserEntity userEntity = userDAO.getUserByName(login);
        UserDto userDto = userConverter.convertToDto(userEntity);
        return userDto;
    }

    public String createUser(UserDto userDto) {
        UserEntity userEntity = userConverter.convertToDbo(userDto);
        UserEntity existedUser = userDAO.getUserByName(userDto.getLogin());
        if (existedUser != null) {
            return "User already exists!";
        }
        else {
            userDAO.save(userEntity);
            return "User was created successfully!";
        }
    }

    public void deleteUser (String login) {
        userDAO.delete(userDAO.getUserByName(login));
    }

}

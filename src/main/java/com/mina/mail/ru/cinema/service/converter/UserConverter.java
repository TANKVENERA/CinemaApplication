package com.mina.mail.ru.cinema.service.converter;

import com.mina.mail.ru.cinema.repository.dbo.UserDbo;
import com.mina.mail.ru.cinema.service.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Mina on 23.04.2019.
 */
@Service
public class UserConverter implements CommonConverter<UserDbo, UserDto> {

    @Override
    public UserDbo convertToDbo(UserDto userDto) {
        UserDbo userDbo = new UserDbo();
        BeanUtils.copyProperties(userDto, userDbo);
        return userDbo;
    }

    @Override
    public UserDto convertToDto(UserDbo userDbo) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDbo, userDto);
        return userDto;
    }
}

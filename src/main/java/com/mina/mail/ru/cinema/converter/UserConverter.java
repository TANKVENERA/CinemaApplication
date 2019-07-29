package com.mina.mail.ru.cinema.converter;

import com.mina.mail.ru.cinema.entity.UserEntity;
import com.mina.mail.ru.cinema.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Mina on 23.04.2019.
 */
@Service
public class UserConverter implements CommonConverter<UserEntity, UserDto> {

    @Override
    public UserEntity convertToDbo(final UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        return userEntity;
    }

    @Override
    public UserDto convertToDto(final UserEntity userEntity) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);
        return userDto;
    }
}

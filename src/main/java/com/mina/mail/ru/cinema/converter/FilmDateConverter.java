package com.mina.mail.ru.cinema.converter;

import com.mina.mail.ru.cinema.entity.FilmDateEntity;
import com.mina.mail.ru.cinema.dto.FilmDateDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author belski M
 */

@Service
public class FilmDateConverter implements CommonConverter<FilmDateEntity, FilmDateDto> {

    @Override
    public FilmDateEntity convertToDbo(final FilmDateDto filmDateDto) {
        final FilmDateEntity filmDateEntity = new FilmDateEntity();
        BeanUtils.copyProperties(filmDateDto, filmDateEntity);
        return filmDateEntity;
    }

    @Override
    public FilmDateDto convertToDto(final FilmDateEntity filmDateEntity) {
        final FilmDateDto filmDateDto = new FilmDateDto();
        BeanUtils.copyProperties(filmDateEntity, filmDateDto);
        return filmDateDto;
    }
}

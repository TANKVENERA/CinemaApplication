package com.mina.mail.ru.cinema.service.converter;

import com.mina.mail.ru.cinema.repository.dbo.FilmEntity;
import com.mina.mail.ru.cinema.service.dto.FilmDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Mina on 29.04.2019.
 */
@Service
public class FilmConverter implements CommonConverter<FilmEntity, FilmDto> {

    @Override
    public FilmEntity convertToDbo(FilmDto filmDto) {
        FilmEntity filmEntity = new FilmEntity();
        BeanUtils.copyProperties(filmDto, filmEntity);
        return filmEntity;
    }

    @Override
    public FilmDto convertToDto(FilmEntity filmEntity) {
        FilmDto filmDto = new FilmDto();
        BeanUtils.copyProperties(filmEntity, filmDto);
        return filmDto;
    }


}

package com.mina.mail.ru.cinema.service.converter;

import com.mina.mail.ru.cinema.repository.dbo.FilmDbo;
import com.mina.mail.ru.cinema.service.dto.FilmDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Mina on 29.04.2019.
 */
@Service
public class FilmConverter implements CommonConverter<FilmDbo, FilmDto> {

    @Override
    public FilmDbo convertToDbo(FilmDto filmDto) {
        FilmDbo filmDbo = new FilmDbo();
        BeanUtils.copyProperties(filmDto, filmDbo);
        return filmDbo;
    }

    @Override
    public FilmDto convertToDto(FilmDbo filmDbo) {
        FilmDto filmDto = new FilmDto();
        BeanUtils.copyProperties(filmDbo, filmDto);
        return filmDto;
    }


}

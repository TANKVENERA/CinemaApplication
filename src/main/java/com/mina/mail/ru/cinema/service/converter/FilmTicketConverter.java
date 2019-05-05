package com.mina.mail.ru.cinema.service.converter;

import com.mina.mail.ru.cinema.repository.dbo.FilmTicketDbo;
import com.mina.mail.ru.cinema.service.dto.FilmTicketDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Mina on 04.05.2019.
 */
@Service
public class FilmTicketConverter implements CommonConverter<FilmTicketDbo, FilmTicketDto> {

    @Override
    public FilmTicketDbo convertToDbo(FilmTicketDto filmTicketDto) {
        FilmTicketDbo filmTicketDbo = new FilmTicketDbo();
        BeanUtils.copyProperties(filmTicketDbo, filmTicketDto);
        return filmTicketDbo;
    }

    @Override
    public FilmTicketDto convertToDto(FilmTicketDbo filmTicketDbo) {
        FilmTicketDto filmTicketDto = new FilmTicketDto();
        BeanUtils.copyProperties(filmTicketDbo, filmTicketDto);
        return filmTicketDto;
    }
}

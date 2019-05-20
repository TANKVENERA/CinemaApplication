package com.mina.mail.ru.cinema.service.converter;

import com.mina.mail.ru.cinema.repository.dbo.FilmTicketEntity;
import com.mina.mail.ru.cinema.service.dto.FilmTicketDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Mina on 04.05.2019.
 */
@Service
public class FilmTicketConverter implements CommonConverter<FilmTicketEntity, FilmTicketDto> {

    @Override
    public FilmTicketEntity convertToDbo(FilmTicketDto filmTicketDto) {
        FilmTicketEntity filmTicketEntity = new FilmTicketEntity();
        BeanUtils.copyProperties(filmTicketEntity, filmTicketDto);
        return filmTicketEntity;
    }

    @Override
    public FilmTicketDto convertToDto(FilmTicketEntity filmTicketEntity) {
        FilmTicketDto filmTicketDto = new FilmTicketDto();
        BeanUtils.copyProperties(filmTicketEntity, filmTicketDto);
        return filmTicketDto;
    }
}

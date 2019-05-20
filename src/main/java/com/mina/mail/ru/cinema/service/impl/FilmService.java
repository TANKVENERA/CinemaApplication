package com.mina.mail.ru.cinema.service.impl;

import com.mina.mail.ru.cinema.repository.dbo.FilmEntity;
import com.mina.mail.ru.cinema.repository.dbo.FilmTicketEntity;
import com.mina.mail.ru.cinema.repository.impl.FilmDAO;
import com.mina.mail.ru.cinema.service.converter.FilmConverter;
import com.mina.mail.ru.cinema.service.converter.FilmTicketConverter;
import com.mina.mail.ru.cinema.service.dto.FilmDto;
import com.mina.mail.ru.cinema.service.dto.FilmTicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Mina on 29.04.2019.
 */

@Service
public class FilmService {

    private FilmDAO filmDAO;
    private FilmConverter filmConverter;
    private FilmTicketConverter filmTicketConverter;

    @Autowired
    public FilmService(FilmDAO filmDAO, FilmConverter converter, FilmTicketConverter filmTicketConverter) {
        this.filmDAO = filmDAO;
        this.filmConverter = converter;
        this.filmTicketConverter = filmTicketConverter;
    }

    public List<FilmDto> getFilms() {
        List<FilmDto> filmsDto = new ArrayList<>();
        List<FilmEntity> filmsDbo = filmDAO.getFilms();

        for (FilmEntity d : filmsDbo) {
            filmsDto.add(filmConverter.convertToDto(d));
        }
        return filmsDto;
    }

    public List<FilmDto> getDatesByFilm(String film) {
        List<FilmDto> dates = new ArrayList<>();
        List<FilmEntity> datesDbo = filmDAO.getDatesByFilm(film);
        for (FilmEntity d : datesDbo) {
            Set<FilmTicketDto> ticketDtos = new HashSet<>();
            for (FilmTicketEntity ticketDbo : d.getTickets()) {
               ticketDtos.add(filmTicketConverter.convertToDto(ticketDbo));
            }
            FilmDto filmDto = filmConverter.convertToDto(d);
            filmDto.setTickets(ticketDtos);
            dates.add(filmDto);
        }
        return dates;
    }

}
